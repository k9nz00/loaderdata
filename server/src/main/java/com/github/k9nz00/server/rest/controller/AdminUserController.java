package com.github.k9nz00.server.rest.controller;

import com.github.k9nz00.server.rest.dto.*;
import com.github.k9nz00.server.service.AdminUserService;
import com.github.k9nz00.server.util.UITransformers;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

import static com.github.k9nz00.server.security.Authorities.MANAGE_USERS;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminUserController {

    private final AdminUserService userService;

    @Secured(MANAGE_USERS)
    @GetMapping("/users")
    public Collection<UserDto> getAllUsers(@ParameterObject final UserCriteriaDto userCriteriaDto) {
        return userService.getAllUsers(userCriteriaDto, UITransformers::userDto);
    }

    @Secured(MANAGE_USERS)
    @PostMapping("/users")
    public CurrentUserDto createUser(@Valid @ParameterObject @RequestBody UserCreateDto userDto) {
        return userService.createUser(userDto);
    }

    @Secured(MANAGE_USERS)
    @GetMapping("/users/{id}")
    public UserDto getUser(@PathVariable int id) {
        return userService.getUser(id);
    }

    @Secured(MANAGE_USERS)
    @PutMapping("/users/{id}")
    public UserDto updateUser(@PathVariable int id,
                              @Valid @ParameterObject @RequestBody UserUpdateDto updateDto) {
        return userService.updateUser(id, updateDto, UITransformers::userDto);
    }

    @Secured(MANAGE_USERS)
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }
}
