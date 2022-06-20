package com.github.k9nz00.server.rest.dto;

import com.github.k9nz00.server.validation.CheckUserExistsValidator;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserCreateDto {

    @Schema(
            name = "roleId",
            required = true,
            allowableValues = {"1", "2"},
            description = "Идентификатор роли для создаваемого пользователя")
    private int roleId;

    @NotBlank
    @CheckUserExistsValidator
    @Length(min = 5, max = 256)
    @Schema(
            name = "name",
            maxLength = 256,
            minLength = 5,
            description = "Имя пользователя для логина на сайте")
    private String name;

    @Length(min = 5)
    @NotNull
    @Schema(description = "Пароль может содержать любые символы", minLength = 5)
    private String password;
}
