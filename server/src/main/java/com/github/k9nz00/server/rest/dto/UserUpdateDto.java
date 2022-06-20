package com.github.k9nz00.server.rest.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserUpdateDto {

    @NotNull
    private int roleId;

    @NotBlank
    private String name;

    @Length(min = 5)
    private String password;
}
