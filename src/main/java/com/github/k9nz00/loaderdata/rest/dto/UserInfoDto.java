package com.github.k9nz00.loaderdata.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

@AllArgsConstructor
@Data
public class UserInfoDto {
    private final String username;
    private final Collection<String> authorities;
}
