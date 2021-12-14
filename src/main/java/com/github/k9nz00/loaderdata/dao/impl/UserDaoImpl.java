package com.github.k9nz00.loaderdata.dao.impl;

import com.github.k9nz00.loaderdata.dao.UserDao;
import com.github.k9nz00.loaderdata.dao.entity.RoleEntity;
import com.github.k9nz00.loaderdata.dao.entity.UserEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager entityManager;

    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public RoleEntity getRole(int roleId) {
        return entityManager.find(RoleEntity.class, roleId);
    }

    @Override
    public boolean userExists(String username) {
        Query nativeQuery = entityManager.createNativeQuery("SELECT exists(SELECT * FROM loader.users WHERE name = :name)");
        nativeQuery.setParameter("name", username);
        return (boolean) nativeQuery.getSingleResult();
    }

    @Override
    public UserEntity createUser(int roleId, String username, String password) {
        Query query = entityManager.createNativeQuery("INSERT INTO loader.users (role_id, name, password) " +
                "values (:role_id, :name, public.crypt(:password, public.gen_salt('bf', 8)))" +
                "returning id, name, password", UserEntity.class);
        query.setParameter("role_id", roleId);
        query.setParameter("name", username);
        query.setParameter("password", password);
        return (UserEntity) query.getSingleResult();
    }

    @Override
    public void deleteUser(int userId) {

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
