package com.isndila.wallet_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "Username cannot be blank.")
    @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters.")
    private String username;

    @NotBlank(message = "Password cannot be blank.")
    @Size(min = 6, message = "Password must be at least 6 characters long.")
    private String password;

    @NotBlank(message = "Email address cannot be blank.")
    @Email(message = "Please provide a valid email address.")
    private String email;

    @NotBlank // Must map directly to supported South African language matrices
    @Pattern(regexp = "ENGLISH|ISIXHOSA|ISIZULU|SESOTHO", message = "Invalid language selection")
    private String preferredLanguage;
}
