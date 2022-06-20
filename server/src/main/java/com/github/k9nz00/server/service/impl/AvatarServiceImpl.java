package com.github.k9nz00.server.service.impl;

import com.github.afkbrb.avatar.Avatar;
import com.github.k9nz00.server.service.AvatarService;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@Service
@Data
public class AvatarServiceImpl implements AvatarService {

    private final Avatar avatarGenerator;

    public AvatarServiceImpl(Avatar avatarGenerator) {
        this.avatarGenerator = avatarGenerator;
    }

    @Override
    public String create() throws IOException {
        BufferedImage image = avatarGenerator.generateAndGetAvatar();
        byte[] bytes = imageToByteArray(image);
        return Base64.getEncoder().encodeToString(bytes);
    }

    private byte[] imageToByteArray(BufferedImage image) throws IOException {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()){
            ImageIO.write(image, "png", out);
            return out.toByteArray();
        }
    }
}
