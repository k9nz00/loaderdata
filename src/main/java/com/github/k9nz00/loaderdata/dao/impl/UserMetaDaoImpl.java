package com.github.k9nz00.loaderdata.dao.impl;

import com.github.k9nz00.loaderdata.dao.type.UserMetaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
public class UserMetaDaoImpl implements UserMetaDao {

    private final EntityManager entityManager;

    @Autowired
    public UserMetaDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public long getCountUsers() {
        Query query = entityManager.createQuery("select count(*) from UserEntity");
        return (long) query.getSingleResult();
    }
}
