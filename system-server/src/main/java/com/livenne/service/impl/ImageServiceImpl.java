package com.livenne.service.impl;

import com.livenne.annotation.Service;
import com.livenne.common.constant.FileConstant;
import com.livenne.service.ImageService;

import java.io.File;

@Service
public class ImageServiceImpl implements ImageService {

    @Override
    public File get(String imageName) {
        return new File(FileConstant.IMAGE_PATH +imageName);
    }

    @Override
    public String getContentType(String imageName) {
        String fileName = imageName.toLowerCase();
        String contentType = "";
        if (fileName.endsWith(".png")) {
            contentType = "image/png";
        } else if (fileName.endsWith(".jpg") ||
                fileName.endsWith(".jpeg")) {
            contentType = "image/jpeg";
        } else if (fileName.endsWith(".gif")) {
            contentType = "image/gif";
        } else {
            contentType = "application/octet-stream";
        }
        return contentType;
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
