package com.yellowdot.yellowdotapi.services;

import com.yellowdot.yellowdotapi.dtos.CreateUserDto;
import com.yellowdot.yellowdotapi.entities.User;

import java.util.List;

public interface UserService {
    void createUser(CreateUserDto userDto);
    List<User> getAllUsers();
}
