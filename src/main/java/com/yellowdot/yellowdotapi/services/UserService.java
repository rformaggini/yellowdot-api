package com.yellowdot.yellowdotapi.services;

import com.yellowdot.yellowdotapi.dtos.CreateUserDto;
import com.yellowdot.yellowdotapi.entities.User;
import com.yellowdot.yellowdotapi.exceptions.LoginException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface UserService {
    void createUser(CreateUserDto userDto) throws LoginException;
    List<User> getAllUsers();
    void updateUsername(String userName, UUID uuid);
}
