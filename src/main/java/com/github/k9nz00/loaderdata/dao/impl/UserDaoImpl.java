package com.github.k9nz00.loaderdata.dao.impl;

import com.github.k9nz00.loaderdata.dao.UserDao;
import com.github.k9nz00.loaderdata.dao.entity.RoleEntity;
import com.github.k9nz00.loaderdata.dao.entity.UserEntity;
import com.github.k9nz00.loaderdata.rest.dto.TableCriteriaDto;
import com.github.k9nz00.loaderdata.util.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.Collection;

@Repository
public class UserDaoImpl extends AbstractDaoImpl implements UserDao {

    @Autowired
    public UserDaoImpl(
            final EntityManager entityManager,
            final @Value("${config.pagination.default-limit}") int defaultLimit) {
        super(entityManager, defaultLimit);
    }

    @Override
    public RoleEntity getRole(int roleId) {
        return entityManager.find(RoleEntity.class, roleId);
    }

    @Override
    public Collection<UserEntity> getAllUsers(TableCriteriaDto tableCriteriaDto) {
        return execute(Transformers.criteriaDto(UserEntity.class, null, tableCriteriaDto));
    }

    @Override
    public boolean userExists(String username) {
        Query nativeQuery = entityManager.createNativeQuery("SELECT exists(SELECT * FROM loader.users WHERE name = :name)");
        nativeQuery.setParameter("name", username);
        return (boolean) nativeQuery.getSingleResult();
    }

    @Override
    public UserEntity createUser(int roleId, String username, String password) {
        Query query = entityManager.createNativeQuery(
                "INSERT INTO loader.users (role_id, name, password) " +
                        "values (:role_id, :name, public.crypt(:password, public.gen_salt('bf', 8)))" +
                        "returning id, role_id, name, password", UserEntity.class);
        query.setParameter("role_id", roleId);
        query.setParameter("name", username);
        query.setParameter("password", password);
        return (UserEntity) query.getSingleResult();
    }

    @Override
    public void deleteUser(int userId) {
        UserEntity userEntity = entityManager.find(UserEntity.class, userId);
        if (userEntity == null){
            throw new IllegalArgumentException(String.format("пользователь с id = %d не найден", userId));
        }
        entityManager.remove(userEntity);
    }

    @Override
    public UserEntity getUserByLoginAndPassword(String username, String password) {
        try {
            Query query = entityManager.createQuery("from UserEntity" +
                    " where name = :name AND password = public.crypt(:password, password)");
            query.setParameter("name", username);
            query.setParameter("password", password);
            Object singleResult = query.getSingleResult();
            return (UserEntity) singleResult;
        } catch (NoResultException e) {
            return null;
        }
    }
}
