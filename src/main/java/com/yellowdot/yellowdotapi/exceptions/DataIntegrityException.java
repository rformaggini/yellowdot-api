package com.yellowdot.yellowdotapi.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class DataIntegrityException extends DataIntegrityViolationException {
    String code;

    public DataIntegrityException(String msg, String code) {
        super(msg);
        this.code = code;
    }
}
