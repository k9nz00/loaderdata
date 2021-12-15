package com.github.k9nz00.loaderdata.rest.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateDto {
    private int roleId;

    @NotBlank
    private String username;

    @Length(min = 5)
    private String password;
}
