package com.github.k9nz00.server.dao.impl;

import com.github.k9nz00.server.dao.PredicateProvider;
import com.github.k9nz00.server.dao.UserDao;
import com.github.k9nz00.server.dao.entity.RoleEntity;
import com.github.k9nz00.server.dao.entity.UserEntity;
import com.github.k9nz00.server.rest.dto.TableCriteriaDto;
import com.github.k9nz00.server.rest.dto.UserUpdateDto;
import com.github.k9nz00.server.util.Transformers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@Repository
@Slf4j
public class UserDaoImpl extends AbstractDaoImpl implements UserDao {

    private final PasswordEncoder encoder;

    @Autowired
    public UserDaoImpl(
            final EntityManager entityManager,
            final @Value("${config.pagination.default-limit}") int defaultLimit,
            final PasswordEncoder passwordEncoder) {
        super(entityManager, defaultLimit);
        this.encoder = passwordEncoder;
    }

    @Override
    public RoleEntity getRole(int roleId) {
        return entityManager.find(RoleEntity.class, roleId);
    }

    @Override
    public Collection<UserEntity> getAllUsers(TableCriteriaDto tableCriteriaDto, PredicateProvider<UserEntity> predicateProvider) {
        return execute(Transformers.criteriaDto(UserEntity.class, predicateProvider, tableCriteriaDto));
    }

    @Override
    public boolean userExists(String username) {
        Query nativeQuery = entityManager.createNativeQuery("SELECT exists(SELECT * FROM loader.users WHERE name = :name and is_active = true)");
        nativeQuery.setParameter("name", username);
        return (boolean) nativeQuery.getSingleResult();
    }

    @Override
    public UserEntity createUser(int roleId, String username, String password) {
        Query query = entityManager.createNativeQuery(
                "INSERT INTO loader.users (role_id, name, password, is_active, created_at) " +
                        "values (:role_id, :name, public.crypt(:password, public.gen_salt('bf', 8)), true, now())" +
                        "returning id, role_id, name, password, is_active, created_at, updated_at, deleted_at", UserEntity.class);
        query.setParameter("role_id", roleId);
        query.setParameter("name", username);
        query.setParameter("password", password);
        return (UserEntity) query.getSingleResult();
    }

    @Override
    public UserEntity createDefaultUser(String username, String password) {
        return createUser(2, username, password);
    }

    @Override
    public UserEntity updateUser(int userId, UserUpdateDto updateDto) {
        UserEntity userEntity = entityManager.find(UserEntity.class, userId);

        if (userEntity == null) {
            throw new IllegalArgumentException(String.format("пользователь с id = %d не найден", userId));
        }

        userEntity.setRoleId(updateDto.getRoleId());
        userEntity.setName(updateDto.getName());
        userEntity.setUpdatedAt(LocalDateTime.now());

        if (updateDto.getPassword() != null) {
            String newPassword = encoder.encode(updateDto.getPassword());
            userEntity.setPassword(newPassword);
        }

        entityManager.persist(userEntity);
        return userEntity;
    }

    @Override
    public void deleteUser(int userId) {
        UserEntity userEntity = entityManager.find(UserEntity.class, userId);
        if (userEntity == null) {
            throw new IllegalArgumentException(String.format("пользователь с id = %d не найден", userId));
        }

        Query nativeQuery = entityManager.createNativeQuery("update loader.users " +
                "set is_active = false, deleted_at = now() " +
                "where id = :userId");
        nativeQuery.setParameter("userId", userId);
        nativeQuery.executeUpdate();
    }

    @Override
    public UserEntity getUserByLoginAndPassword(String username, String password) {
        try {
            Query query = entityManager.createQuery("from UserEntity" +
                    " where name = :name AND password = public.crypt(:password, password) AND is_active = true");
            query.setParameter("name", username);
            query.setParameter("password", password);
            Object singleResult = query.getSingleResult();
            return (UserEntity) singleResult;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public UserEntity getUserByLogin(String username) {
        Query query = entityManager.createQuery("from UserEntity where name =:name");
        query.setParameter("name", username);
        try {
            return (UserEntity) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public UserEntity getUser(int userId) {
        try {
            return entityManager.find(UserEntity.class, userId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Optional<Integer> getUserIdByUserName(String username) {
        try {
            return Optional.of(entityManager.createQuery("select u.id from UserEntity u where u.name = :name", Integer.class)
                    .setParameter("name", username)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
