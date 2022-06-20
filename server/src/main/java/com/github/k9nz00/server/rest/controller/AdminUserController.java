package com.github.k9nz00.server.rest.controller;

import com.github.k9nz00.server.rest.dto.*;
import com.github.k9nz00.server.service.AdminUserService;
import com.github.k9nz00.server.util.UITransformers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(
        name = "AdminUserController",
        description = "Контроллер для административных работ. " +
                "Предоставляет максимальный функционал для управления пользователями. " +
                "При расширении функционала необходимо обновить описание")
public class AdminUserController {

    private final AdminUserService userService;

    @Secured(MANAGE_USERS)
    @GetMapping("/users")
    @Operation(summary = "Позвляет получить всех пользователей")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Возвращает список зарегистрированных пользователей")
    })
    public Collection<UserDto> getAllUsers(@ParameterObject @Parameter final UserCriteriaDto userCriteriaDto) {
        return userService.getAllUsers(userCriteriaDto, UITransformers::userDto);
    }

    @Secured(MANAGE_USERS)
    @PostMapping("/users")
    @Operation(
            summary = "Создание пользователя"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Пользователь успешно создан")
    })
    public CurrentUserDto createUser(@Valid @RequestBody UserCreateDto userDto) {
        return userService.createUser(userDto);
    }

    @Secured(MANAGE_USERS)
    @GetMapping("/users/{id}")
    @Operation(summary = "Получение информации по конкретному пользователю")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Полная информация о пользователе")
    })
    public UserDto getUser(
            @PathVariable
            @Parameter(
                    name = "id",
                    example = "1",
                    description = "Идентификатор пользователя",
                    required = true) int id) {
        return userService.getUser(id);
    }

    @Secured(MANAGE_USERS)
    @PutMapping("/users/{id}")
    public UserDto updateUser(@PathVariable int id,
                              @Valid @RequestBody UserUpdateDto updateDto) {
        return userService.updateUser(id, updateDto, UITransformers::userDto);
    }

    @Secured(MANAGE_USERS)
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }
}
