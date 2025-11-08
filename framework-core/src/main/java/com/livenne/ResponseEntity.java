package com.livenne;

public class ResponseEntity<T> {
    private Integer status;
    private String message;
    private T data;

    public static <T> ResponseEntity<T> success(T data) {
        return new ResponseEntity<T>(ResponseStatus.SUCCESS,null,data);
    }
    public static <T> ResponseEntity<T> successMsg(String message) {
        return new ResponseEntity<T>(ResponseStatus.SUCCESS,message,null);
    }
    public static <T> ResponseEntity<T> success(T data, String message) {
        return new ResponseEntity<T>(ResponseStatus.SUCCESS,message,data);
    }

    public static <T> ResponseEntity<T> failure(T data) {
        return new ResponseEntity<T>(ResponseStatus.FAILURE,null,data);
    }
    public static <T> ResponseEntity<T> failureMsg(String message) {
        return new ResponseEntity<T>(ResponseStatus.FAILURE,message,null);
    }
    public static <T> ResponseEntity<T> failure(T data, String message) {
        return new ResponseEntity<T>(ResponseStatus.FAILURE,message,data);
    }

    public ResponseEntity(){}

    public ResponseEntity(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
