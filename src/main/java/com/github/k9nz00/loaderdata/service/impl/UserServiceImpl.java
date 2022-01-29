package com.github.k9nz00.loaderdata.service.impl;

import com.github.k9nz00.loaderdata.dao.PredicateProvider;
import com.github.k9nz00.loaderdata.dao.UserDao;
import com.github.k9nz00.loaderdata.dao.entity.UserEntity;
import com.github.k9nz00.loaderdata.factory.CriteriaPredicateFactory;
import com.github.k9nz00.loaderdata.rest.dto.*;
import com.github.k9nz00.loaderdata.service.UserService;
import com.github.k9nz00.loaderdata.transformer.Transformer;
import com.github.k9nz00.loaderdata.transformer.dto.LoggedUserTransformerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final CriteriaPredicateFactory<UserCriteriaDto, UserEntity> userCriteriaPredicateFactory;
    private final Transformer<LoggedUserTransformerDto, CurrentUserDto> loginUserTransformer;


    @Override
    public <T> T createUser(UserCreateDto dto, Function<UserEntity, T> transformer) {
        UserEntity user = userDao.createUser(dto.getRoleId(), dto.getUsername(), dto.getPassword());
        return transformer.apply(user);
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

    @Override
    public <T> T updateUser(int userId, UserUpdateDto dto, Function<UserEntity, T> transformer) {
        UserEntity userEntity = userDao.updateUser(userId, dto);
        return transformer.apply(userEntity);
    }

    @Override
    public void deleteById(int userId) {
        userDao.deleteUser(userId);
    }

    @Override
    public <T> Collection<T> getAllUsers(UserCriteriaDto criteriaDto, Function<UserEntity, T> transformer) {

        PredicateProvider<UserEntity> entityPredicateProvider = userCriteriaPredicateFactory.create(criteriaDto);
        return userDao.getAllUsers(criteriaDto, entityPredicateProvider).stream()
                .map(transformer)
                .collect(Collectors.toList());
    }
}
