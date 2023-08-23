package com.pokedex.authservice.controller;

import com.pokedex.authservice.dto.ForgotPasswordDto;
import com.pokedex.authservice.dto.LoginRequestDto;
import com.pokedex.authservice.dto.RegisterRequestDto;
import com.pokedex.authservice.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto dto){
        return new ResponseEntity<>(authService.login(dto), HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto dto){
        return new ResponseEntity<>(authService.register(dto), HttpStatus.OK);
    }

    @PostMapping("/forgotpassword")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordDto dto){
        return new ResponseEntity<>(authService.forgotPassword(dto), HttpStatus.OK);
    }

}
