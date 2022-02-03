package com.github.k9nz00.loaderdata.service;

import com.github.k9nz00.loaderdata.dao.entity.UserEntity;
import com.github.k9nz00.loaderdata.rest.dto.*;

import java.util.Collection;
import java.util.function.Function;

public interface AdminUserService {
    CurrentUserDto getUserByLogin(UserRequestDto requestDto);
    <T> Collection<T> getAllUsers(UserCriteriaDto criteriaDto, Function<UserEntity, T> transformer);
    CurrentUserDto createUser(UserCreateDto dto);
    <T> T updateUser(int userId, UserUpdateDto dto, Function<UserEntity, T> transformer);
    void deleteUser(int userId);
}
