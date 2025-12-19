package com.livenne.controller;

import com.livenne.service.ImageService;
import io.github.livenne.ResponseEntity;
import io.github.livenne.annotation.container.*;
import io.github.livenne.annotation.servlet.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Controller("/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/{imageName}")
    public void download(@PathVariable("imageName") String imageName, @Response HttpServletResponse response) {
        if (!imageService.has(imageName)) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        File image = imageService.get(imageName);
        long fileLength = image.length();
        response.setContentLengthLong(fileLength);
        response.setContentType(imageService.getContentType(imageName));
        response.setHeader("Cache-Control", "public, max-age=300");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        try (FileInputStream fis = new FileInputStream(image);
             OutputStream out = response.getOutputStream()) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            if (!response.isCommitted()) {
                out.flush();
            }
        } catch (IOException e) {
            log.error("Error streaming image", e);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity upload(@Request HttpServletRequest request) throws ServletException, IOException {
        Part part = request.getPart("file");
        String fileName = part.getSubmittedFileName();
        String newFileName = UUID.randomUUID().toString().replace("-","") + fileName.substring(fileName.lastIndexOf('.'));
        if (newFileName.isBlank()) return ResponseEntity.err().message("上传的图片后缀有误");
        InputStream inputStream = part.getInputStream();
        imageService.upload(inputStream, newFileName);
        return ResponseEntity.ok(newFileName);
    }
}
