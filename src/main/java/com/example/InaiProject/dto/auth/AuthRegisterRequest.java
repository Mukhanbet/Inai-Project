package com.example.InaiProject.dto.auth;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AuthRegisterRequest {
    @NotNull
    @Size(min = 3, max = 15, message = "Invalid first name! (3-15 characters)")
    private String firstName;
    @NotNull
    @Size(min = 3, max = 15, message = "Invalid last name! (3-15 characters)")
    private String lastName;
    @NotNull
    @Size(min = 10, max = 25, message = "Invalid email! (10-25 characters")
    private String email;
    @NotNull
    @Size(min = 6, max = 15, message = "Invalid password! (6-15 characters)")
    private String password;
    @Size(min = 6, max = 15, message = "Invalid password! (6-15 characters)")
    private String confirmPassword;
}
