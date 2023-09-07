package com.pokedex.mailservice.dto;

import lombok.Data;

@Data
public class ForgotPasswordEmailDto {
    private String toEmail;
    private String name;
    private String newPassword;
}
