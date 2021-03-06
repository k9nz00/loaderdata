package com.github.k9nz00.server.service.impl;

import com.github.k9nz00.server.dao.PredicateProvider;
import com.github.k9nz00.server.dao.UserDao;
import com.github.k9nz00.server.dao.entity.UserEntity;
import com.github.k9nz00.server.factory.CriteriaPredicateFactory;
import com.github.k9nz00.server.rest.dto.*;
import com.github.k9nz00.server.service.AdminUserService;
import com.github.k9nz00.server.transformer.Transformer;
import com.github.k9nz00.server.transformer.dto.LoggedUserTransformerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {

    private final UserDao userDao;
    private final CriteriaPredicateFactory<UserCriteriaDto, UserEntity> userCriteriaPredicateFactory;
    private final Transformer<LoggedUserTransformerDto, CurrentUserDto> loginUserTransformer;
    private final Transformer<UserEntity, UserDto> userTransformer;

    @Override
    public CurrentUserDto getUserByLogin(UserRequestDto requestDto) {
        return null;
    }

    @Override
    public <T> Collection<T> getAllUsers(UserCriteriaDto criteriaDto, Function<UserEntity, T> transformer) {

        PredicateProvider<UserEntity> entityPredicateProvider = userCriteriaPredicateFactory.create(criteriaDto);
        return userDao.getAllUsers(criteriaDto, entityPredicateProvider).stream()
                .map(transformer)
                .collect(Collectors.toList());
    }

    @Override
    public CurrentUserDto createUser(UserCreateDto dto) {
        UserEntity user = userDao.createUser(dto.getRoleId(), dto.getName(), dto.getPassword());
        return loginUserTransformer.transform(new LoggedUserTransformerDto(dto.getPassword(), user));
    }

    @Override
    public UserDto getUser(int userId) {
        return Optional.ofNullable(userDao.getUser(userId))
                .map(userTransformer::transform)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException(String.format("???????????????????????? ?? id = %d ???? ????????????", userId));
                });
    }

    @Override
    public <T> T updateUser(int userId, UserUpdateDto dto, Function<UserEntity, T> transformer) {
        userDao.getUserIdByUserName(dto.getName())
                .ifPresent(id -> {
                    if (id != userId) {
                        throw new IllegalArgumentException(
                                String.format("User with username = '%s' already exists. Choose a different username",
                                        dto.getName()));
                    }
                });
        UserEntity userEntity = userDao.updateUser(userId, dto);
        return transformer.apply(userEntity);
    }

    @Override
    public void deleteUser(int userId) {
        userDao.deleteUser(userId);
    }
}
