package com.github.k9nz00.loaderdata.service;

import com.github.k9nz00.loaderdata.rest.dto.CurrentUserDto;
import com.github.k9nz00.loaderdata.rest.dto.UserRequestDto;

public interface UserService {
    CurrentUserDto getUser(UserRequestDto requestDto);
}
