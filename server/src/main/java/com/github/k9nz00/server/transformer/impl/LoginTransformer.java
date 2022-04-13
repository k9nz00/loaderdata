package com.github.k9nz00.server.transformer.impl;

import com.github.k9nz00.server.dao.UserDao;
import com.github.k9nz00.server.dao.entity.RoleEntity;
import com.github.k9nz00.server.dao.entity.UserEntity;
import com.github.k9nz00.server.rest.dto.CurrentUserDto;
import com.github.k9nz00.server.transformer.Transformer;
import com.github.k9nz00.server.transformer.dto.LoggedUserTransformerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class LoginTransformer implements Transformer<LoggedUserTransformerDto, CurrentUserDto> {

    private final UserDao userDao;

    @Override
    public CurrentUserDto transform(LoggedUserTransformerDto input) {
        String requestedPassword = input.getRequestedPassword();
        UserEntity userEntity = input.getUserEntity();

        CurrentUserDto currentUserDto = new CurrentUserDto();
        currentUserDto.setId(userEntity.getId());
        currentUserDto.setRoleId(userEntity.getRoleId());
        currentUserDto.setName(userEntity.getName());
        currentUserDto.setCreatedAt(userEntity.getCreatedAt());

        if (userEntity.getUpdatedAt() != null) {
            currentUserDto.setUpdatedAt(userEntity.getUpdatedAt());
        }

        if (userEntity.getDeletedAt() != null) {
            currentUserDto.setDeletedAt(userEntity.getDeletedAt());
        }

        RoleEntity role = userDao.getRole(userEntity.getRoleId());
        currentUserDto.setAuthorities(Arrays.asList(role.getAuthorities()));
        currentUserDto.setBasicAuthToken(Base64.getEncoder().encodeToString(String.format("%s:%s", userEntity.getName(), requestedPassword).getBytes(StandardCharsets.UTF_8)));
        return currentUserDto;
    }
}
