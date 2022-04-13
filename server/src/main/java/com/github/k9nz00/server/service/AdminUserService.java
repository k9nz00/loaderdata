package com.github.k9nz00.server.service;

import com.github.k9nz00.server.dao.entity.UserEntity;
import com.github.k9nz00.server.rest.dto.*;

import java.util.Collection;
import java.util.function.Function;

public interface AdminUserService {
    CurrentUserDto getUserByLogin(UserRequestDto requestDto);
    <T> Collection<T> getAllUsers(UserCriteriaDto criteriaDto, Function<UserEntity, T> transformer);
    CurrentUserDto createUser(UserCreateDto dto);
    UserDto getUser(int userId);
    <T> T updateUser(int userId, UserUpdateDto dto, Function<UserEntity, T> transformer);
    void deleteUser(int userId);
}
