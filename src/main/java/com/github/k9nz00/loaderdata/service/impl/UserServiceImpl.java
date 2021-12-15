package com.github.k9nz00.loaderdata.service.impl;

import com.github.k9nz00.loaderdata.dao.UserDao;
import com.github.k9nz00.loaderdata.dao.entity.RoleEntity;
import com.github.k9nz00.loaderdata.dao.entity.UserEntity;
import com.github.k9nz00.loaderdata.rest.dto.CurrentUserDto;
import com.github.k9nz00.loaderdata.rest.dto.UserCreateDto;
import com.github.k9nz00.loaderdata.rest.dto.UserCriteriaDto;
import com.github.k9nz00.loaderdata.rest.dto.UserRequestDto;
import com.github.k9nz00.loaderdata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public <T> T createUser(UserCreateDto dto, Function<UserEntity, T> transformer) {
        UserEntity user = userDao.createUser(dto.getRoleId(), dto.getUsername(), dto.getPassword());
        return transformer.apply(user);
    }

    @Override
    public CurrentUserDto getUserByLogin(UserRequestDto requestDto) {
        if (!userDao.userExists(requestDto.getUsername())) {
            throw new IllegalArgumentException(String.format("Пользователь с логином '%s'  не существует", requestDto.getUsername()));
        }
        UserEntity user = this.userDao.getUserByLoginAndPassword(requestDto.getUsername(), requestDto.getPassword());
        return Optional.ofNullable(user)
                .map(userEntity -> {
                    CurrentUserDto currentUserDto = new CurrentUserDto();
                    currentUserDto.setUserId(user.getId());
                    currentUserDto.setRoleId(user.getRoleId());
                    currentUserDto.setUsername(user.getName());

                    RoleEntity role = userDao.getRole(user.getRoleId());
                    currentUserDto.setAuthorities(Arrays.asList(role.getAuthorities()));
                    return currentUserDto;
                })
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("Неправильный логин или пароль");
                });
    }

    @Override
    public void deleteById(int userId) {
        userDao.deleteUser(userId);
    }

    @Override
    public <T> Collection<T> getAllUsers(UserCriteriaDto criteriaDto, Function<UserEntity, T> transformer) {
        return userDao.getAllUsers(criteriaDto).stream()
                .map(transformer)
                .collect(Collectors.toList());
    }
}
