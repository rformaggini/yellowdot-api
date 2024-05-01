package com.yellowdot.yellowdotapi.infra;

import com.yellowdot.yellowdotapi.enums.ErrorCode;
import com.yellowdot.yellowdotapi.exceptions.LoginException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(LoginException.class)
    private ResponseEntity<ResponseErrorMessage> loginHandler(LoginException exception){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body(new ResponseErrorMessage(ErrorCode.LG001.getCode(), ErrorCode.LG001.getMessage()));
    }
}
