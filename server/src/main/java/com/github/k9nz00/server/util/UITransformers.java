package com.github.k9nz00.server.util;

import com.github.k9nz00.server.dao.entity.UserEntity;
import com.github.k9nz00.server.rest.dto.UserDto;
import com.github.k9nz00.server.rest.dto.UserInfoDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UITransformers {

    public static UserDto userDto(final UserEntity input) {
        return new UserDto(
                input.getId(),
                input.getRoleId(),
                input.getAvatarId(),
                input.getName(),
                input.getIsActive(),
                input.getCreatedAt(),
                input.getUpdatedAt(),
                input.getDeletedAt()
        );
    }

    public static UserInfoDto userDto(final Authentication authentication) {
        if (authentication != null){
            Collection<String> authorityList = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
            return new UserInfoDto(authentication.getName(), authorityList);
        } else {
            return null;
        }
    }
}
