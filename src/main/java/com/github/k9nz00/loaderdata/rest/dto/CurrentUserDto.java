package com.github.k9nz00.loaderdata.rest.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CurrentUserDto extends UserDto {
    private String basicAuthToken;
    private List<String> authorities;
}
