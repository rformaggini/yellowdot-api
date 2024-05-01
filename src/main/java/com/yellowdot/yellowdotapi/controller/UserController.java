package com.yellowdot.yellowdotapi.controller;

import com.yellowdot.yellowdotapi.dtos.CreateUserDto;
import com.yellowdot.yellowdotapi.entities.Role;
import com.yellowdot.yellowdotapi.entities.User;
import com.yellowdot.yellowdotapi.repositories.RoleRepository;
import com.yellowdot.yellowdotapi.repositories.UserRepository;
import com.yellowdot.yellowdotapi.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@RestController("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDto userDto){
        userService.createUser(userDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<User>> listUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
