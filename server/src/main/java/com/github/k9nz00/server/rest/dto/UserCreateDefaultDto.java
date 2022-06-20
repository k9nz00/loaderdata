package com.github.k9nz00.server.rest.dto;

import com.github.k9nz00.server.validation.CheckUserExistsValidator;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class UserCreateDefaultDto {
    @NotBlank
    @CheckUserExistsValidator
    private String username;

    @Length(min = 5)
    private String password;
}
