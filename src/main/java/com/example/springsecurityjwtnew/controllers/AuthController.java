package com.example.springsecurityjwtnew.controllers;


import com.example.springsecurityjwtnew.dto.AuthResponseDTO;
import com.example.springsecurityjwtnew.dto.LoginDto;
import com.example.springsecurityjwtnew.dto.RegisterDto;
import com.example.springsecurityjwtnew.dto.ResponseDto;
import com.example.springsecurityjwtnew.security.JWTGenerator;
import com.example.springsecurityjwtnew.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JWTGenerator jwtGenerator;
    private final UserService userService;


    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<ResponseDto> register(@RequestBody RegisterDto registerDto) {

        try {
            userService.saveUser(registerDto);
            return new ResponseEntity<ResponseDto>(new ResponseDto("User registered success!", null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<ResponseDto>(new ResponseDto(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
