package com.github.k9nz00.server.dao.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AvatarDto {
    private Integer id;
    private Integer userId;
    private String filename;
    private String avatarData;
    private Long size;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
