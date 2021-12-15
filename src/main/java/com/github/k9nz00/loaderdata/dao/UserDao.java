package com.github.k9nz00.loaderdata.dao;

import com.github.k9nz00.loaderdata.dao.entity.RoleEntity;
import com.github.k9nz00.loaderdata.dao.entity.UserEntity;
import com.github.k9nz00.loaderdata.rest.dto.TableCriteriaDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface UserDao {


    RoleEntity getRole(int roleId);

    Collection<UserEntity> getAllUsers(TableCriteriaDto tableCriteriaDto);

    boolean userExists(String username);

    @Transactional
    UserEntity createUser(int roleId, String username, String password);

    //update

    //блокировка пользователя

    @Transactional
    void deleteUser(int userId);

    @Transactional
    UserEntity getUserByLoginAndPassword(String username, String password);
}
