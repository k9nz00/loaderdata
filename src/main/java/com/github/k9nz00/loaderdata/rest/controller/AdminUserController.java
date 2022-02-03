package com.github.k9nz00.loaderdata.rest.controller;

import com.github.k9nz00.loaderdata.rest.dto.*;
import com.github.k9nz00.loaderdata.service.AdminUserService;
import com.github.k9nz00.loaderdata.service.UserService;
import com.github.k9nz00.loaderdata.util.UITransformers;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

import static com.github.k9nz00.loaderdata.security.Authorities.MANAGE_USERS;

@RestController
@RequestMapping("/api/users")
public class AdminUserController {

    private final AdminUserService userService;

    @Autowired
    public AdminUserController(AdminUserService userService) {
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

    @Secured(MANAGE_USERS)
    @GetMapping
    public Collection<UserDto> getAllUsers(@ParameterObject final UserCriteriaDto userCriteriaDto) {
        return userService.getAllUsers(userCriteriaDto, UITransformers::userDto);
    }

    @PostMapping
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
        userService.deleteById(id);
    }
}
