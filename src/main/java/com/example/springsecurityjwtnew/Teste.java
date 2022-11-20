package com.example.springsecurityjwtnew;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Teste {
    public static void main(String[] args) {
        String senha = "Teste123";
        String encode = new BCryptPasswordEncoder().encode(senha);
        encode.toString();
    }
}
