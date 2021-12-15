package com.github.k9nz00.loaderdata.util;

import com.github.k9nz00.loaderdata.dao.entity.UserEntity;
import com.github.k9nz00.loaderdata.rest.dto.UserDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UITransformers {

    public static UserDto userDto(final UserEntity input) {
        return new UserDto(input.getId(), input.getRoleId(), input.getName());
    }
}
