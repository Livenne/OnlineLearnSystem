package com.livenne.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class StringUtils{
    public static ObjectMapper mapper = new ObjectMapper();


    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T formJson(String json,TypeReference<T> typeReference) {
        try {
            return mapper.readValue(json, typeReference);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T formJson(String json,Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            if (e instanceof JsonParseException) {
                try {
                    String wrappedJson = "\"" + json + "\"";
                    return mapper.readValue(wrappedJson, clazz);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return null;
                }
            }
            e.printStackTrace();
            return null;
        }
    }

    public static String getBody(HttpServletRequest req) {
        int contentLength = req.getContentLength();
        byte[] bytes = new byte[contentLength];

        try (ServletInputStream inputStream = req.getInputStream()) {
            inputStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
