package com.github.k9nz00.loaderdata.rest.dto;

import com.github.k9nz00.loaderdata.validation.CheckUserExistsValidator;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class UserCreateDto {

    private int roleId;

    @NotBlank
    @CheckUserExistsValidator
    private String username;

    @Length(min = 5)
    private String password;
}
