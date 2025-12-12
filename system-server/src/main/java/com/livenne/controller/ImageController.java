package com.livenne.controller;

import com.livenne.annotation.*;
import com.livenne.service.ImageService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
@Controller("/image")
public class ImageController{

    @Autowired
    private ImageService imageService;
    @Autowired
    private HttpServletResponse response;

    @GetMapping("/{imageName}")
    public void download(@PathVariable("imageName") String imageName) {
        if (!imageService.has(imageName)) return;
        File image = imageService.get(imageName);
        long fileLength = image.length();
        response.setContentLengthLong(fileLength);
//        response.setHeader("Cache-Control", "public, max-age=300");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Content-Type", imageService.getContentType(imageName));
        System.out.println(imageService.getContentType(imageName));
        System.out.println(response.getContentType());

        try (FileInputStream fis = new FileInputStream(image);
             OutputStream out = response.getOutputStream()) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            out.flush();
        } catch (IOException e) {
            log.error("Error streaming image", e);
        }
    }
}
