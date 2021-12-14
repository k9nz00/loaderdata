package com.github.k9nz00.loaderdata.rest.dto;

import lombok.Data;

import java.util.List;

@Data
public class CurrentUserDto {

    private int userId;
    private int roleId;
    private String username;
    private List<String> authorities;
}
