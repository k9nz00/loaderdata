package com.github.k9nz00.server.dao;

import com.github.k9nz00.server.dao.entity.RoleEntity;
import com.github.k9nz00.server.dao.entity.UserEntity;
import com.github.k9nz00.server.rest.dto.TableCriteriaDto;
import com.github.k9nz00.server.rest.dto.UserUpdateDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

public interface UserDao {

    RoleEntity getRole(int roleId);

    Collection<UserEntity> getAllUsers(TableCriteriaDto tableCriteriaDto, PredicateProvider<UserEntity> predicateProvider);

    boolean userExists(String username);

    @Transactional
    UserEntity createUser(int roleId, String username, String password);

    @Transactional
    UserEntity createDefaultUser(String username, String password);

    @Transactional
    UserEntity updateUser(int userId, UserUpdateDto updateDto);

    //блокировка пользователя

    @Transactional
    void deleteUser(int userId);

    @Transactional
    UserEntity getUserByLoginAndPassword(String username, String password);

    UserEntity getUserByLogin(String username);

    UserEntity getUser(int userId);

    Optional<Integer> getUserIdByUserName(String username);
}
