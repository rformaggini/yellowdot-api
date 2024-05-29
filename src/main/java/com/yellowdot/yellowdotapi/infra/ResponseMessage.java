package com.yellowdot.yellowdotapi.infra;

public class ResponseMessage<T> {
    private String code;
    private String message;
    private T data;
    private boolean hasError;

    public ResponseMessage(){
    }

    public ResponseMessage(String code, String message, boolean hasError) {
        this.code = code;
        this.message = message;
        this.hasError = hasError;
    }

    public ResponseMessage(String code, String message, T data, boolean hasError) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.hasError = hasError;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
