package com.example.springsecurityjwtnew.service;

import com.example.springsecurityjwtnew.dto.RegisterDto;
import com.example.springsecurityjwtnew.enums.RoleName;
import com.example.springsecurityjwtnew.models.Role;
import com.example.springsecurityjwtnew.models.UserEntity;
import com.example.springsecurityjwtnew.repository.RoleRepository;
import com.example.springsecurityjwtnew.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void saveUser(RegisterDto registerDto) {

        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new RuntimeException("Username is taken!");
        }

        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));

        Role roles = roleRepository.findByRoleName(RoleName.ROLE_USER).orElse(null);
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);
    }

}
