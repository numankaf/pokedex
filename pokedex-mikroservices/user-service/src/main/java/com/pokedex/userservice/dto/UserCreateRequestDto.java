package com.pokedex.userservice.dto;

import com.pokedex.userservice.enums.UserRole;
import lombok.Data;

@Data
public class UserCreateRequestDto {
    private String username;
    private String email;
    private String name;
    private String surname;
    private String password;
    private UserRole role;
}
