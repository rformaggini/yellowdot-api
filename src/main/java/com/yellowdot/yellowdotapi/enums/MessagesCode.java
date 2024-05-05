package com.yellowdot.yellowdotapi.enums;

public enum MessagesCode {

    LG001("Username or email already in use.", "LG-001"),
    LG002("User created successfully.", "LG-002"),
    LG003("User data or password invalid.", "LG-003")
    ;
    private String message;
    private String code;

    MessagesCode(String message, String code) {
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
