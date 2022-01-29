package com.github.k9nz00.loaderdata.transformer.impl;

import com.github.k9nz00.loaderdata.dao.UserDao;
import com.github.k9nz00.loaderdata.dao.entity.RoleEntity;
import com.github.k9nz00.loaderdata.dao.entity.UserEntity;
import com.github.k9nz00.loaderdata.rest.dto.CurrentUserDto;
import com.github.k9nz00.loaderdata.transformer.Transformer;
import com.github.k9nz00.loaderdata.transformer.dto.LoggedUserTransformerDto;
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
        currentUserDto.setUserId(userEntity.getId());
        currentUserDto.setRoleId(userEntity.getRoleId());
        currentUserDto.setUsername(userEntity.getName());
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