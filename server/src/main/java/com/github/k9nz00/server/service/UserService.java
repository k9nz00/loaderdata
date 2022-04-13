package com.github.k9nz00.server.service;

import com.github.k9nz00.server.rest.dto.*;

public interface UserService {
    CurrentUserDto getUserByLogin(UserRequestDto requestDto);
    CurrentUserDto createUser(UserCreateDefaultDto dto);
}
