package com.github.k9nz00.server.service.impl;

import com.github.k9nz00.server.dao.AvatarDao;
import com.github.k9nz00.server.dao.UserDao;
import com.github.k9nz00.server.dao.dto.AvatarDto;
import com.github.k9nz00.server.dao.entity.AvatarEntity;
import com.github.k9nz00.server.dao.entity.UserEntity;
import com.github.k9nz00.server.rest.dto.CurrentUserDto;
import com.github.k9nz00.server.rest.dto.UserCreateDefaultDto;
import com.github.k9nz00.server.rest.dto.UserRequestDto;
import com.github.k9nz00.server.service.AvatarService;
import com.github.k9nz00.server.service.UserService;
import com.github.k9nz00.server.transformer.Transformer;
import com.github.k9nz00.server.transformer.dto.LoggedUserTransformerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final AvatarDao avatarDao;
    private final Transformer<LoggedUserTransformerDto, CurrentUserDto> loginUserTransformer;
    private final AvatarService avatarService;

    @Override
    public CurrentUserDto createUser(UserCreateDefaultDto dto) {
        UserEntity user = userDao.createDefaultUser(dto.getUsername(), dto.getPassword());
        try {
            AvatarDto userAvatar = getUserAvatar(user);
            AvatarEntity avatar = avatarDao.save(userAvatar);
            userDao.setAvatarId(user.getId(), avatar.getId());
        } catch (IOException e) {
            log.error("Error creation avatar {}", e.getMessage());
            throw new RuntimeException(e);
        }
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

    private AvatarDto getUserAvatar(UserEntity user) throws IOException {
        String avatarData = avatarService.create();
        return AvatarDto
                .builder()
                .userId(user.getId())
                .avatarData(avatarData)
                .filename(UUID.randomUUID().toString())
                .createdAt(LocalDateTime.now())
                .size(Long.parseLong(String.valueOf(avatarData.length())))
                .build();
    }
}
