package com.yellowdot.yellowdotapi.controllers;

import com.yellowdot.yellowdotapi.dtos.LoginRequest;
import com.yellowdot.yellowdotapi.dtos.LoginResponse;
import com.yellowdot.yellowdotapi.enums.MessagesCode;
import com.yellowdot.yellowdotapi.infra.ResponseMessage;
import com.yellowdot.yellowdotapi.services.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/login")
public class TokenController {

    private final LoginService loginService;

    public TokenController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<ResponseMessage<LoginResponse>> login(@RequestBody LoginRequest loginRequest){
        var response = new ResponseMessage<LoginResponse>();
        response.setData(loginService.login(loginRequest));
        response.setMessage(MessagesCode.LG004.getMessage());
        response.setCode(MessagesCode.LG004.getCode());
        return ResponseEntity.ok(response);
    }
}
