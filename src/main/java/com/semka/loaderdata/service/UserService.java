package com.semka.loaderdata.service;

import com.semka.loaderdata.rest.dto.CurrentUserDto;
import com.semka.loaderdata.rest.dto.UserRequestDto;

public interface UserService {
    CurrentUserDto getUser(UserRequestDto requestDto);
}
