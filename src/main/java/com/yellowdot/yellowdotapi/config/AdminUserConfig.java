package com.yellowdot.yellowdotapi.config;

import com.yellowdot.yellowdotapi.entities.Role;
import com.yellowdot.yellowdotapi.entities.User;
import com.yellowdot.yellowdotapi.enums.UserStatus;
import com.yellowdot.yellowdotapi.repositories.RoleRepository;
import com.yellowdot.yellowdotapi.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public AdminUserConfig(RoleRepository roleRepository,
                           UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        var userAdmin = userRepository.findUserByUsername("admin@test.com");

        userAdmin.ifPresentOrElse(
                (user) -> {
                    System.out.println("SET USER ADMIN DONE. -> USERNAME: " + user.getUsername());
                },
                () -> {
                    var roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());
                    var user = new User();
                    user.setName("ADMIN");
                    user.setContactNumber("");
                    user.setUsername("admin@test.com");
                    user.setStatus(UserStatus.ACTIVE);
                    user.setPassword(passwordEncoder.encode("admin"));
                    user.setEmail("admin@test.com");
                    user.setRoles(Set.of(roleAdmin));

                    userRepository.save(user);
                }
        );

    }
}
