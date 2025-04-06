package com.extia.easyshopper.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank
        String name,
        @Email
        @NotBlank
        String email,
        @Size(min = 8)
        String password) {
}
