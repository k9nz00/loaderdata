package com.github.k9nz00.server.transformer.dto;

import com.github.k9nz00.server.dao.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoggedUserTransformerDto {
    private String requestedPassword;
    private UserEntity userEntity;
}
