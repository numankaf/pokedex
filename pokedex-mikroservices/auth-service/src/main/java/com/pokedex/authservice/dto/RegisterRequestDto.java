package com.pokedex.authservice.dto;

import lombok.Data;

@Data
public class RegisterRequestDto {
    private String username;
    private String email;
    private String name;
    private String surname;
    private String password;
}
