package com.livenne.service;

import java.io.File;
import java.io.IOException;

public interface ImageService {


    File get(String imageName);

    String getContentType(String imageName);

    Boolean has(String imageName);

    void upload(String imageName);
}
