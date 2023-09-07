package com.pokedex.authservice.dto;

import com.pokedex.authservice.enums.UserRole;
import lombok.Data;

@Data
public class LoginResponseDto {
    private String username;
    private String token;
    private UserRole role;
}
