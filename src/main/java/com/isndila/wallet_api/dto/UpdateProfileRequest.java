package com.isndila.wallet_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdateProfileRequest {
    @NotBlank
    @Pattern(regexp = "ENGLISH|ISIXHOSA|ISIZULU|SESOTHO")
    private String preferredLanguage;
}
