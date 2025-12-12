package com.livenne.service.impl;

import com.livenne.annotation.Service;
import com.livenne.common.constant.FileConstant;
import com.livenne.service.ImageService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class ImageServiceImpl implements ImageService {

    @Override
    public File get(String imageName) {
        return new File(FileConstant.IMAGE_PATH +imageName);
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
    public void upload(String imageName) {

    }
}
