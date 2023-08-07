package com.pokedex.controller;

import com.pokedex.dto.auth.LoginRequestDto;
import com.pokedex.dto.auth.RegisterRequestDto;
import com.pokedex.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto dto){
        System.out.println("called");
        return new ResponseEntity<>(authService.login(dto), HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto dto){
        return new ResponseEntity<>(authService.register(dto), HttpStatus.OK);
    }
}
