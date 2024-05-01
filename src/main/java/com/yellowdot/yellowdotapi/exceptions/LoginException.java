package com.yellowdot.yellowdotapi.exceptions;

public class LoginException extends Exception {

    private  String code;

    public LoginException(String message, String code) {
        super(message);
        this.code = code;
    }
}
