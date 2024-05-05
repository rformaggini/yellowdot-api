package com.yellowdot.yellowdotapi.repositories;

import com.yellowdot.yellowdotapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByUsernameOrEmail(String username, String email);

}
