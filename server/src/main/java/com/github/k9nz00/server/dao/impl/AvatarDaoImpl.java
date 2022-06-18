package com.github.k9nz00.server.dao.impl;

import com.github.k9nz00.server.dao.AvatarDao;
import com.github.k9nz00.server.dao.dto.AvatarDto;
import com.github.k9nz00.server.dao.entity.AvatarEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class AvatarDaoImpl implements AvatarDao {

    private final EntityManager entityManager;

    @Override
    @Transactional
    public AvatarEntity save(AvatarDto avatarDto) {
        AvatarEntity avatar = new AvatarEntity();
        avatar.setUserId(avatarDto.getUserId());
        avatar.setAvatarData(avatarDto.getAvatarData());
        avatar.setCreatedAt(avatarDto.getCreatedAt());
        avatar.setFilename(avatarDto.getFilename());
        avatar.setSize(avatarDto.getSize());

        entityManager.persist(avatar);
        return avatar;
    }

    @Override
    @Transactional
    public AvatarEntity update(AvatarDto avatarDto) {
        return null;
    }

    @Override
    public AvatarEntity getById(int avatarId) {
        return entityManager.find(AvatarEntity.class, avatarId);
    }
}
