package com.livenne.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Predicate;

@Slf4j
public class StringUtils{
    public static ObjectMapper mapper = new ObjectMapper();

    public static ObjectNode resultSetRowToJsonObject(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        ObjectNode jsonObject = mapper.createObjectNode();

        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnLabel(i);
            Object value = rs.getObject(i);

            if (value == null) {
                jsonObject.putNull(columnName);
            } else if (value instanceof String) {
                jsonObject.put(columnName, (String) value);
            } else if (value instanceof Number) {
                if (value instanceof Integer) {
                    jsonObject.put(columnName, (Integer) value);
                } else if (value instanceof Long) {
                    jsonObject.put(columnName, (Long) value);
                } else if (value instanceof Double) {
                    jsonObject.put(columnName, (Double) value);
                } else if (value instanceof Float) {
                    jsonObject.put(columnName, (Float) value);
                }
            } else if (value instanceof Boolean) {
                jsonObject.put(columnName, (Boolean) value);
            } else {
                jsonObject.set(columnName, mapper.valueToTree(value));
            }
        }
        return jsonObject;
    }

    public static ArrayNode resultSetToJsonArray(ResultSet rs) throws SQLException {
        ArrayNode jsonArray = mapper.createArrayNode();

        while (rs.next()) {
            ObjectNode jsonObject = resultSetRowToJsonObject(rs);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    public static <T> T jsonNodeToObject(JsonNode jsonNode, TypeReference<T> typeReference)
            throws JsonProcessingException {
        return mapper.readValue(jsonNode.toString(), typeReference);
    }

    public static <T> T resultSetToObject(ResultSet rs, TypeReference<T> typeReference)
            throws SQLException, JsonProcessingException {

        ArrayNode jsonArray = resultSetToJsonArray(rs);
        return jsonNodeToObject(jsonArray, typeReference);
    }

    public static <T> T resultSetToObject(ResultSet rs, Class<T> clazz)
            throws SQLException, JsonProcessingException {

        ArrayNode jsonArray = resultSetToJsonArray(rs);
        return mapper.treeToValue(jsonArray, clazz);
    }

    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return null;
        }
    }

    public static <T> T formJson(String json,TypeReference<T> typeReference) {
        try {
            return mapper.readValue(json, typeReference);
        } catch (Exception e) {
            if (e instanceof JsonParseException) {
                try {
                    String wrappedJson = "\"" + json + "\"";
                    return mapper.readValue(wrappedJson, typeReference);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return null;
                }
            }
            e.printStackTrace();
            return null;
        }
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

    public static Boolean isPathVariable(String path) {
        return !path.isBlank() && path.startsWith("{") && path.endsWith("}");
    }

    public static Boolean isPathEquals(String req,String api){
        String[] reqArr = Arrays.stream(req.split("/")).filter(Predicate.not(String::isBlank)).toArray(String[]::new);
        String[] apiArr = Arrays.stream(api.split("/")).filter(Predicate.not(String::isBlank)).toArray(String[]::new);
        if (reqArr.length != apiArr.length) return false;
        for (int i = 0; i < reqArr.length; i++) {
            if (!reqArr[i].equals(apiArr[i]) && !isPathVariable(apiArr[i])) return false;
        }
        return true;
    }

    public static HashMap<String,String> pathVarMap(String req,String api){
        HashMap<String,String> map = new HashMap<>();
        if (!isPathEquals(req,api)) return map;
        String[] reqArr = Arrays.stream(req.split("/")).filter(Predicate.not(String::isBlank)).toArray(String[]::new);
        String[] apiArr = Arrays.stream(api.split("/")).filter(Predicate.not(String::isBlank)).toArray(String[]::new);
        for (int i = 0; i < reqArr.length; i++) {
            if (isPathVariable(apiArr[i])) {
                map.put(apiArr[i].substring(1,apiArr[i].length()-1), reqArr[i]);
            }
        }
        return map;
    }

    public static Object toObject(String str,Class<?> type){
        if (type.equals(String.class)) return str;
        if (type.equals(Integer.class)) return Integer.parseInt(str);
        if (type.equals(int.class)) return Integer.parseInt(str);
        if (type.equals(Double.class)) return Double.parseDouble(str);
        if (type.equals(double.class)) return Double.parseDouble(str);
        if (type.equals(Float.class)) return Float.parseFloat(str);
        if (type.equals(float.class)) return Float.parseFloat(str);
        if (type.equals(Boolean.class)) return Boolean.parseBoolean(str);
        if (type.equals(boolean.class)) return Boolean.parseBoolean(str);
        if (type.equals(Long.class)) return Long.parseLong(str);
        if (type.equals(long.class)) return Long.parseLong(str);
        if (type.equals(Character.class)) return str.charAt(0);
        if (type.equals(char.class)) return str.charAt(0);
        return formJson(str,type);
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
