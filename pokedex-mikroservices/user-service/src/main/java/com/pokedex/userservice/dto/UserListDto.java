package com.pokedex.userservice.dto;

import com.pokedex.userservice.enums.UserRole;
import lombok.Data;

@Data
public class UserListDto {
    private Long id;
    private String username;
    private String email;
    private String name;
    private String surname;
    private String thumbnail;
    private UserRole role;

}
