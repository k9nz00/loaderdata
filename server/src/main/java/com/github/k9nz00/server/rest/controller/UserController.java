package com.github.k9nz00.server.rest.controller;

import com.github.k9nz00.server.rest.dto.*;
import com.github.k9nz00.server.service.UserService;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.validation.Valid;
import java.io.IOException;

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

    @PostMapping(value = "/register")
    public CurrentUserDto createUser(@Valid @RequestBody UserCreateDefaultDto userDto) {
        //return userService.createUser(userDto);
        return null;
    }

    @PostMapping(value = "/register-with-avatar", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public CurrentUserDto createUserWithAvatar(@Valid @RequestPart UserCreateDefaultDto userDto, @RequestPart MultipartFile avatar ) {
        //return userService.createUser(userDto);
        return null;
    }
}
