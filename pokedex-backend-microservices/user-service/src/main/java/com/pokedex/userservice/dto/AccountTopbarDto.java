package com.pokedex.userservice.dto;

import com.pokedex.userservice.enums.UserRole;
import lombok.Data;

@Data
public class AccountTopbarDto {
    private String username;
    private UserRole role;
    private String thumbnail;
}
