package com.github.k9nz00.server.rest.dto;

import com.github.k9nz00.server.validation.CheckUserExistsValidator;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class UserCreateDto {

    @Parameter(
            name = "roleId",
            required = true,
            description = "Идентификатор роли для создаваемого пользователя"
    )
    private int roleId;

    @NotBlank
    @CheckUserExistsValidator
    private String name;

    @Length(min = 5)
    private String password;
}
