package com.yellowdot.yellowdotapi.services;

import com.yellowdot.yellowdotapi.dtos.LoginRequest;
import com.yellowdot.yellowdotapi.dtos.LoginResponse;


public interface LoginService {
    LoginResponse login(LoginRequest loginRequest);
}
