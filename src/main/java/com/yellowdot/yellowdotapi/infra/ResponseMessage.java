package com.yellowdot.yellowdotapi.infra;

public class ResponseMessage {
    private String code;
    private String message;
    private boolean hasError;

    public ResponseMessage(String code, String message, boolean hasError) {
        this.code = code;
        this.message = message;
        this.hasError = hasError;
    }

    public ResponseMessage(){

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
}
