package com.yellowdot.yellowdotapi.infra;

import com.yellowdot.yellowdotapi.enums.MessagesCode;
import com.yellowdot.yellowdotapi.exceptions.EntityNotFoundException;
import com.yellowdot.yellowdotapi.exceptions.InvalidCredentialException;
import com.yellowdot.yellowdotapi.exceptions.LoginException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(LoginException.class)
    private ResponseEntity<ResponseMessage> loginHandler(LoginException exception){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body(new ResponseMessage(MessagesCode.LG001.getCode(), MessagesCode.LG001.getMessage(), true));
    }

    @ExceptionHandler(InvalidCredentialException.class)
    private ResponseEntity<ResponseMessage> credentialHandler(InvalidCredentialException exception){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ResponseMessage(MessagesCode.LG003.getCode(), MessagesCode.LG003.getMessage(), true));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<ResponseMessage> dataBaseHandler(EntityNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseMessage(MessagesCode.DB001.getCode(), MessagesCode.DB001.getMessage(), true));
    }
}
