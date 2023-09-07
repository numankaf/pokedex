package com.pokedex.userservice.dto;

import lombok.Data;

@Data
public class ChangePasswordDto {
    private String currentPassword;
    private String newPassword;
}
