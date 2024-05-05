package com.yellowdot.yellowdotapi.services;

import com.yellowdot.yellowdotapi.dtos.LoginRequest;
import com.yellowdot.yellowdotapi.dtos.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    LoginResponse login(LoginRequest loginRequest);
}
