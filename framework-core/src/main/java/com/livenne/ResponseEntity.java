package com.livenne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class ResponseEntity<T> {
    private Integer status;
    private String message;
    private T data;

    public ResponseEntity(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseEntity<T> success() {
        return new ResponseEntity<>(ResponseStatus.SUCCESS, null, null);
    }

    public static <T> ResponseEntity<T> success(T data) {
        return new ResponseEntity<>(ResponseStatus.SUCCESS, null, data);
    }

    public static <T> ResponseEntity<T> successMsg(String message) {
        return new ResponseEntity<>(ResponseStatus.SUCCESS, message, null);
    }

    public static <T> ResponseEntity<T> success(T data, String message) {
        return new ResponseEntity<>(ResponseStatus.SUCCESS, message, data);
    }

    public static <T> ResponseEntity<T> failure() {
        return new ResponseEntity<>(ResponseStatus.FAILURE, null, null);
    }

    public static <T> ResponseEntity<T> failure(T data) {
        return new ResponseEntity<>(ResponseStatus.FAILURE, null, data);
    }

    public static <T> ResponseEntity<T> failureMsg(String message) {
        return new ResponseEntity<>(ResponseStatus.FAILURE, message, null);
    }

    public static <T> ResponseEntity<T> failure(T data, String message) {
        return new ResponseEntity<>(ResponseStatus.FAILURE, message, data);
    }
}