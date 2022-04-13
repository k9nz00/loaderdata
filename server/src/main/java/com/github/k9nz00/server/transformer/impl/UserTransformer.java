package com.github.k9nz00.server.transformer.impl;

import com.github.k9nz00.server.dao.entity.UserEntity;
import com.github.k9nz00.server.rest.dto.UserDto;
import com.github.k9nz00.server.transformer.Transformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserTransformer implements Transformer<UserEntity, UserDto> {

    @Override
    public UserDto transform(UserEntity input) {

        return new UserDto(
                input.getId(),
                input.getRoleId(),
                input.getName(),
                input.getIsActive(),
                input.getCreatedAt(),
                input.getUpdatedAt(),
                input.getDeletedAt()
        );
    }
}
