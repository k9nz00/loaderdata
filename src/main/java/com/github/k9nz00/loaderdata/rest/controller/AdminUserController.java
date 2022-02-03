package com.github.k9nz00.loaderdata.rest.controller;

import com.github.k9nz00.loaderdata.rest.dto.*;
import com.github.k9nz00.loaderdata.service.AdminUserService;
import com.github.k9nz00.loaderdata.util.UITransformers;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

import static com.github.k9nz00.loaderdata.security.Authorities.MANAGE_USERS;

@RestController
@RequestMapping("/api/admin")
public class AdminUserController {

    private final AdminUserService userService;

    @Autowired
    public AdminUserController(AdminUserService userService) {
        this.userService = userService;
    }

    @Secured(MANAGE_USERS)
    @GetMapping("/users")
    public Collection<UserDto> getAllUsers(@ParameterObject final UserCriteriaDto userCriteriaDto) {
        return userService.getAllUsers(userCriteriaDto, UITransformers::userDto);
    }

    @Secured(MANAGE_USERS)
    @PostMapping("/user")
    public UserDto createUser(@Valid @ParameterObject @RequestBody UserCreateDto userDto) {
        return userService.createUser(userDto, UITransformers::userDto);
    }

    @Secured(MANAGE_USERS)
    @PutMapping("/user/{id}")
    public UserDto updateUser(@PathVariable int id,
                              @Valid @ParameterObject @RequestBody UserUpdateDto updateDto) {
        return userService.updateUser(id, updateDto, UITransformers::userDto);
    }

    @Secured(MANAGE_USERS)
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }
}
