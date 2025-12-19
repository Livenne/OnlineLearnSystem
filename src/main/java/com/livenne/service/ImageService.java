package com.livenne.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface ImageService {


    File get(String imageName);

    String getContentType(String imageName);

    Boolean has(String imageName);

    void upload(InputStream inputStream,String imageName) throws IOException;

    String getFileExtensionFromContentType(String contentType);
}
