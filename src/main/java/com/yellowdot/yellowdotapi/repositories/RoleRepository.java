package com.yellowdot.yellowdotapi.repositories;

import com.yellowdot.yellowdotapi.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
