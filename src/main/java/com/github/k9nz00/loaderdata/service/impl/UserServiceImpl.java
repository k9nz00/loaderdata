package com.github.k9nz00.loaderdata.service.impl;

import com.github.k9nz00.loaderdata.dao.UserDao;
import com.github.k9nz00.loaderdata.dao.entity.UserEntity;
import com.github.k9nz00.loaderdata.rest.dto.CurrentUserDto;
import com.github.k9nz00.loaderdata.rest.dto.UserCreateDefaultDto;
import com.github.k9nz00.loaderdata.rest.dto.UserRequestDto;
import com.github.k9nz00.loaderdata.service.UserService;
import com.github.k9nz00.loaderdata.transformer.Transformer;
import com.github.k9nz00.loaderdata.transformer.dto.LoggedUserTransformerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final Transformer<LoggedUserTransformerDto, CurrentUserDto> loginUserTransformer;

    @Override
    public CurrentUserDto createUser(UserCreateDefaultDto dto) {
        UserEntity user = userDao.createDefaultUser(dto.getUsername(), dto.getPassword());
        return loginUserTransformer.transform(new LoggedUserTransformerDto(dto.getPassword(), user));
    }

    @Override
    public CurrentUserDto getUserByLogin(UserRequestDto requestDto) {
        if (!userDao.userExists(requestDto.getUsername())) {
            throw new IllegalArgumentException(String.format("Пользователь с логином '%s'  не существует", requestDto.getUsername()));
        }
        UserEntity user = this.userDao.getUserByLoginAndPassword(requestDto.getUsername(), requestDto.getPassword());
        return Optional.ofNullable(user)
                .map(userEntity -> loginUserTransformer.transform(new LoggedUserTransformerDto(requestDto.getPassword(), userEntity)))
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("Неправильный логин или пароль");
                });
    }
}
