package com.example.springsecurityjwtnew.controllers;

import com.example.springsecurityjwtnew.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
@RequiredArgsConstructor
public class HelloController {

    @GetMapping("/test")
    public ResponseEntity<ResponseDto> test() {
        return ResponseEntity.ok(new ResponseDto("Hello", null));
    }

}
