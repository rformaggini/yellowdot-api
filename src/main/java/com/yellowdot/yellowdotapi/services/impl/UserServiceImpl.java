package com.yellowdot.yellowdotapi.services.impl;

import com.yellowdot.yellowdotapi.dtos.CreateUserDto;
import com.yellowdot.yellowdotapi.entities.Role;
import com.yellowdot.yellowdotapi.entities.User;
import com.yellowdot.yellowdotapi.enums.MessagesCode;
import com.yellowdot.yellowdotapi.enums.UserStatus;
import com.yellowdot.yellowdotapi.exceptions.LoginException;
import com.yellowdot.yellowdotapi.repositories.RoleRepository;
import com.yellowdot.yellowdotapi.repositories.UserRepository;
import com.yellowdot.yellowdotapi.services.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

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
    public void createUser(CreateUserDto userDto) throws LoginException {

        var basicRole = roleRepository.findByName(Role.Values.BASIC.name());
        var staffRole = roleRepository.findByName(Role.Values.STAFF.name());
        var userFromBD = userRepository.findUserByUsernameOrEmail(userDto.email(), userDto.email());

        if(userFromBD.isPresent()){
            throw new LoginException(MessagesCode.LG001.getCode(), MessagesCode.LG001.getMessage());
        }

        var user = new User();
        user.setEmail(userDto.email());
        user.setUsername(userDto.email());
        user.setContactNumber(userDto.contactNumber());
        user.setName(userDto.name());
        user.setPassword(passwordEncoder.encode(userDto.password()));
        user.setRoles(Set.of(basicRole,staffRole));
        user.setStatus(UserStatus.ON_APPROVAL);
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void updateUsername(String userName, UUID uuid) {

    }
}
