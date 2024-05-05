package com.yellowdot.yellowdotapi.exceptions;

public class EntityNotFoundException extends Exception{
    private String code;


    public EntityNotFoundException(String msg, String code) {
        super(msg);
        this.code = code;

    }


}
