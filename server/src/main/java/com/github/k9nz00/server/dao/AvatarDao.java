package com.github.k9nz00.server.dao;

import com.github.k9nz00.server.dao.dto.AvatarDto;
import com.github.k9nz00.server.dao.entity.AvatarEntity;
import org.springframework.transaction.annotation.Transactional;

public interface AvatarDao {
    @Transactional
    AvatarEntity save(AvatarDto avatarDto);

    @Transactional
    AvatarEntity update(AvatarDto avatarDto);

    AvatarEntity getById(int avatarId);
}
