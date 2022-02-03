package com.github.k9nz00.loaderdata.service;

import com.github.k9nz00.loaderdata.dao.entity.UserEntity;
import com.github.k9nz00.loaderdata.rest.dto.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.function.Function;

public interface UserService {
    CurrentUserDto getUserByLogin(UserRequestDto requestDto);
    CurrentUserDto createUser(UserCreateDefaultDto dto);
}
