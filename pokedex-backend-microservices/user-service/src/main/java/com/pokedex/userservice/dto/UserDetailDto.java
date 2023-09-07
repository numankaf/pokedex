package com.pokedex.userservice.dto;

import com.pokedex.userservice.enums.UserRole;
import lombok.Data;

@Data
public class UserDetailDto {
    private Long id;

    private String createdBy;

    private String createdDate;

    private String lastModifiedBy;

    private String lastModifiedDate;

    private Boolean isActive;

    String username;

    String email;

    String name;

    String surname;

    String about;

    String thumbnail;

    UserRole role;
}
