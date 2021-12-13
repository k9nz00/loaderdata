package com.semka.loaderdata.rest.controller;

import com.semka.loaderdata.rest.dto.CurrentUserDto;
import com.semka.loaderdata.rest.dto.UserRequestDto;
import com.semka.loaderdata.service.UserService;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return userService.getUser(requestDto);
    }
}
