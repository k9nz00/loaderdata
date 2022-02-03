package com.github.k9nz00.loaderdata.rest.controller;

import com.github.k9nz00.loaderdata.rest.dto.*;
import com.github.k9nz00.loaderdata.service.UserService;
import com.github.k9nz00.loaderdata.util.UITransformers;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public CurrentUserDto login(@ParameterObject @RequestBody UserRequestDto requestDto) {
        return userService.getUserByLogin(requestDto);
    }

    @GetMapping("/currentUser")
    public UserInfoDto getCurrentUserInfo(Authentication authentication) {
        return UITransformers.userDto(authentication);
    }

    @PostMapping("/register")
    public UserDto createUser(@Valid @ParameterObject @RequestBody UserCreateDefaultDto userDto) {
        return userService.createUser(userDto, UITransformers::userDto);
    }
}
