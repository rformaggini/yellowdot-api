package com.yellowdot.yellowdotapi.enums;

public enum ErrorCode {

    LG001("Username or email not already in use.", "LG-001"),
    LG002("Username, email or login invalid.", "LG-002"),
    ;
    private String message;
    private String code;

    ErrorCode(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
