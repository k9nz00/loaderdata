package com.github.k9nz00.loaderdata.transformer.dto;

import com.github.k9nz00.loaderdata.dao.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoggedUserTransformerDto {
    private String requestedPassword;
    private UserEntity userEntity;
}
