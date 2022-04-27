package com.github.k9nz00.server.rest.controller;

import com.github.k9nz00.server.rest.dto.*;
import com.github.k9nz00.server.service.UserService;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
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
    public CurrentUserDto login(@RequestBody UserRequestDto requestDto) {
        return userService.getUserByLogin(requestDto);
    }

    @PostMapping("/register")
    public CurrentUserDto createUser(@Valid @RequestBody UserCreateDefaultDto userDto) {
        return userService.createUser(userDto);
    }
}
