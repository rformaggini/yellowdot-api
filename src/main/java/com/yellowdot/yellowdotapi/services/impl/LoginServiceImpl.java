package com.yellowdot.yellowdotapi.services.impl;

import com.yellowdot.yellowdotapi.dtos.LoginRequest;
import com.yellowdot.yellowdotapi.dtos.LoginResponse;
import com.yellowdot.yellowdotapi.entities.Role;
import com.yellowdot.yellowdotapi.enums.MessagesCode;
import com.yellowdot.yellowdotapi.exceptions.InvalidCredentialException;
import com.yellowdot.yellowdotapi.repositories.UserRepository;
import com.yellowdot.yellowdotapi.services.LoginService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class LoginServiceImpl implements LoginService {

    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public LoginServiceImpl(JwtEncoder jwtEncoder, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        var user = userRepository.findUserByUsername(loginRequest.username());

        if(user.isEmpty() || !user.get().isLoginCorrect(loginRequest, passwordEncoder)){
            throw new InvalidCredentialException(MessagesCode.LG003.getMessage(), MessagesCode.LG003.getCode());
        }

        var now = Instant.now();
        var expiresIn = 3000L;

        var scopes = user.get().getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("pub-online-api")
                .issuedAt(now)
                .subject(user.get().getUserId().toString())
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scope", scopes)
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new LoginResponse(jwtValue, expiresIn);
    }
}
