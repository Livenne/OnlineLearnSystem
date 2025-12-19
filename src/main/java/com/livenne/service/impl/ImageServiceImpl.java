package com.livenne.service.impl;

import com.livenne.service.ImageService;
import io.github.livenne.annotation.container.Service;
import io.github.livenne.annotation.container.Value;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class ImageServiceImpl implements ImageService {

    @Value("image.path")
    public String IMAGE_PATH;

    @Override
    public File get(String imageName) {
        return new File(IMAGE_PATH + imageName);
    }

    @Override
    public String getContentType(String imageName) {
        if (imageName.toLowerCase().endsWith(".jpg") || imageName.toLowerCase().endsWith(".jpeg")) {
            return "image/jpeg";
        } else if (imageName.toLowerCase().endsWith(".png")) {
            return "image/png";
        } else if (imageName.toLowerCase().endsWith(".gif")) {
            return "image/gif";
        } else if (imageName.toLowerCase().endsWith(".webp")) {
            return "image/webp";
        }
        return "image/jpeg";
    }

    @Override
    public Boolean has(String imageName) {
        File file = get(imageName);
        return file != null;
    }

    @Override
    public void upload(InputStream inputStream, String imageName) throws IOException {
        Files.copy(inputStream, Paths.get(IMAGE_PATH + imageName));
    }

    @Override
    public String getFileExtensionFromContentType(String contentType) {
        if (contentType == null) {
            return ".dat";
        }
        return switch (contentType.toLowerCase()) {
            case "image/jpeg", "image/jpg" -> ".jpg";
            case "image/png" -> ".png";
            case "image/gif" -> ".gif";
            case "image/webp" -> ".webp";
            default -> {
                if (contentType.startsWith("image/")) {
                    yield "." + contentType.substring(6);
                }
                yield ".dat";
            }
        };
    }
}
