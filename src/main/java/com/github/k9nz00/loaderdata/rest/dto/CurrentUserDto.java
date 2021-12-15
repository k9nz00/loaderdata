package com.github.k9nz00.loaderdata.rest.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CurrentUserDto extends UserDto {
    private List<String> authorities;
}
