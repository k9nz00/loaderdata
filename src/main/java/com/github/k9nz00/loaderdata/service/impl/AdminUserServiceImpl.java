package com.github.k9nz00.loaderdata.service.impl;

import com.github.k9nz00.loaderdata.dao.entity.UserEntity;
import com.github.k9nz00.loaderdata.rest.dto.*;
import com.github.k9nz00.loaderdata.service.AdminUserService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.function.Function;

@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Override
    public CurrentUserDto getUserByLogin(UserRequestDto requestDto) {
        return null;
    }

    @Override
    public <T> Collection<T> getAllUsers(UserCriteriaDto criteriaDto, Function<UserEntity, T> transformer) {
        return null;
    }

    @Override
    public <T> T createUser(UserCreateDto dto, Function<UserEntity, T> transformer) {
        return null;
    }

    @Override
    public <T> T updateUser(int userId, UserUpdateDto dto, Function<UserEntity, T> transformer) {
        return null;
    }

    @Override
    public void deleteById(int userId) {

    }
}
