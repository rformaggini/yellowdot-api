package com.yellowdot.yellowdotapi.exceptions;

import org.springframework.security.authentication.BadCredentialsException;

public class InvalidCredentialException extends BadCredentialsException {
    private String code;

    public InvalidCredentialException(String msg, String code) {
        super(msg);
        this.code = code;
    }
}
