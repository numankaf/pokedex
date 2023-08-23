package com.pokedex.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPasswordEmailDto {
    private String toEmail;
    private String name;
    private String newPassword;
}
