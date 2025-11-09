package com.livenne.controller;

import com.livenne.annotation.Autowired;
import com.livenne.annotation.Controller;
import com.livenne.annotation.GetMapping;
import com.livenne.annotation.RequestParm;
import com.livenne.service.ImageService;
import com.livenne.service.impl.ImageServiceImpl;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;

@Controller("/image")
public class ImageController{
    public ImageService imageService = ImageServiceImpl.instance;
    @Autowired
    public HttpServletResponse response;

    @GetMapping
    public void download(@RequestParm("imageName") String imageName) {
        if (!imageService.has(imageName)) return;
        File image = imageService.get(imageName);
        response.setContentType(imageService.getContentType(imageName));
        try (FileInputStream fis = new FileInputStream(image)) {
            response.setContentLength((int) image.length());
            OutputStream out = response.getOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException("Failed to stream image data", e);
        }
    }
}
