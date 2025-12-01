package com.livenne.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Set;

public class TestClient {

    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException, InterruptedException {
        File file = new File("C:\\Users\\21332\\Downloads\\win.zip");
        upload(file, 1024 * 1024);
    }

    private static void upload(File file, int chuckSize) throws IOException, InterruptedException {
        String fileName = file.getName();
        long fileSize = file.length();
        int totalChuck = (int) Math.ceil((double) fileSize / chuckSize);

        Set<Integer> uploadedSet = checkProgress(fileName);

        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {

            byte[] buffer = new byte[chuckSize];

            for (int i = 0; i < totalChuck; i++) {
                if (uploadedSet.contains(i)) {
                    System.out.println("Skip chuck:" + i);
                    continue;
                }

                int byteRead = raf.read(buffer,0,chuckSize);
                if (byteRead == -1) break;

                uploadFile(fileName, i, byteRead == chuckSize ? buffer : Arrays.copyOf(buffer, byteRead));
                Thread.sleep(2000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        mergeFile(fileName);
        System.out.println("Upload file complete!");

    }

    private static Set<Integer> checkProgress(String fileName) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("localhost:8080/file/check/" + fileName))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(response.body(), new TypeReference<>() {
        });
    }

    private static void uploadFile(String fileName, int chuckIndex, byte[] chunkData) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        // 构建请求体：这里假设你的框架能解析这种简单的格式，或者需要调整为框架能识别的格式
        String boundary = "----JavaHttpClientBoundary" + System.currentTimeMillis();
        ByteArrayOutputStream requestBody = new ByteArrayOutputStream();
        String bodyText = "--" + boundary + "\r\n" +
                "Content-Disposition: form-data; name=\"fileData\"; filename=\"chunk.dat\"\r\n" +
                "Content-Type: application/octet-stream\r\n\r\n";
        requestBody.write(bodyText.getBytes(StandardCharsets.UTF_8));
        requestBody.write(chunkData); // 写入文件数据

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/file/upload?fileName="+fileName+"&chuckIndex="+chuckIndex)) // 确保有 http://
                .header("Content-Type", "multipart/form-data; boundary=" + boundary)
                .POST(HttpRequest.BodyPublishers.ofByteArray(requestBody.toByteArray()))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("分块 " + chuckIndex + " 上传，响应状态: " + response.statusCode());
    }

    private static void mergeFile(String fileName) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("localhost:8080/file/merge/" + fileName))
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }


}
