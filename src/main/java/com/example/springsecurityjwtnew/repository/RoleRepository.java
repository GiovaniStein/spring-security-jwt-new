package com.example.springsecurityjwtnew.repository;

import com.example.springsecurityjwtnew.enums.RoleName;
import com.example.springsecurityjwtnew.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(RoleName roleName);
}
