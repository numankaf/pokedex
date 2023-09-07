package com.pokedex.userservice.dto;

import lombok.Data;

@Data
public class UserUpdateRequestDto {
    private String name;

    private String surname;

    private String about;

    private String thumbnail;
}
