package com.example.InaiProject.dto.auth;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AuthLoginRequest {
    @NotNull
    @Size(min = 10, max = 25, message = "Invalid email! (10-25 characters")
    private String email;
    @NotNull
    @Size(min = 6, max = 15, message = "Invalid password! (6-15 characters)")
    private String password;
}
