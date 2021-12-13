package com.semka.loaderdata.dao;

import com.semka.loaderdata.dao.entity.RoleEntity;
import com.semka.loaderdata.dao.entity.UserEntity;
import org.springframework.transaction.annotation.Transactional;

public interface UserDao {


    RoleEntity getRole(int roleId);

    @Transactional
    UserEntity createUser(int roleId, String username, String password);

    //update

    //блокировка пользователя

    @Transactional
    void deleteUser(int userId);

    @Transactional
    UserEntity getUserByLoginAndPassword(String username, String password);
}
