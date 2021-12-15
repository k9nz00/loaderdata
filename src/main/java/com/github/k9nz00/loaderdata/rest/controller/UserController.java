package com.github.k9nz00.loaderdata.rest.controller;

import com.github.k9nz00.loaderdata.rest.dto.*;
import com.github.k9nz00.loaderdata.service.UserService;
import com.github.k9nz00.loaderdata.util.UITransformers;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Collections;

import static com.github.k9nz00.loaderdata.security.Authorities.MANAGE_USERS;

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

    @Secured(MANAGE_USERS)
    @GetMapping
    public Collection<UserDto> getAllUsers(@ParameterObject final UserCriteriaDto userCriteriaDto) {
        return userService.getAllUsers(userCriteriaDto, UITransformers::userDto);
    }

    @Secured(MANAGE_USERS)
    @PostMapping
    public UserDto createUser(@Valid @ParameterObject @RequestBody UserCreateDto userDto) {
        return userService.createUser(userDto, UITransformers::userDto);
    }
}
