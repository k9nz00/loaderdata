package com.github.k9nz00.loaderdata.service.impl;

import com.github.k9nz00.loaderdata.dao.UserDao;
import com.github.k9nz00.loaderdata.dao.entity.RoleEntity;
import com.github.k9nz00.loaderdata.dao.entity.UserEntity;
import com.github.k9nz00.loaderdata.rest.dto.CurrentUserDto;
import com.github.k9nz00.loaderdata.rest.dto.UserRequestDto;
import com.github.k9nz00.loaderdata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public CurrentUserDto getUser(UserRequestDto requestDto) {
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
}