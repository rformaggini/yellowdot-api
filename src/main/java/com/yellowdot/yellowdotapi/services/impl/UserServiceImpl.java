package com.yellowdot.yellowdotapi.services.impl;

import com.yellowdot.yellowdotapi.dtos.CreateUserDto;
import com.yellowdot.yellowdotapi.entities.Role;
import com.yellowdot.yellowdotapi.entities.User;
import com.yellowdot.yellowdotapi.enums.UserStatus;
import com.yellowdot.yellowdotapi.repositories.RoleRepository;
import com.yellowdot.yellowdotapi.repositories.UserRepository;
import com.yellowdot.yellowdotapi.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl( UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createUser(CreateUserDto userDto) {

        var basicRole = roleRepository.findByName(Role.Values.BASIC.name());
        var userFromBD = userRepository.findUserByUsername(userDto.username());

        if(userFromBD.isPresent()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        var user = new User();
        user.setEmail(userDto.email());
        user.setUsername(userDto.username());
        user.setPassword(passwordEncoder.encode(userDto.password()));
        user.setRoles(Set.of(basicRole));
        user.setStatus(UserStatus.ACTIVE);
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
