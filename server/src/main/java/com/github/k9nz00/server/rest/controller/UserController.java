package com.github.k9nz00.server.rest.controller;

import com.github.k9nz00.server.rest.dto.CurrentUserDto;
import com.github.k9nz00.server.rest.dto.UserCreateDefaultDto;
import com.github.k9nz00.server.rest.dto.UserRequestDto;
import com.github.k9nz00.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping(value = "/register")
    public CurrentUserDto createUser(@Valid @RequestBody UserCreateDefaultDto userDto) {
        return userService.createUser(userDto);
    }

    @PostMapping(value = "/{userId}/upload-avatar", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public CurrentUserDto uploadAvatar(@PathVariable String userId, @RequestPart MultipartFile avatar) {

        //return userService.createUser(userDto);
        return null;
    }
}
