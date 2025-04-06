package com.extia.easyshopper.application.dto.request;

import jakarta.validation.constraints.*;

public record RegisterRequest(
        @NotBlank(message = "The name must not be blank.")
        String name,
        @Email(message = "The value must be a valid e-mail address.")
        @NotBlank(message = "The e-mail must not be blank.")
        String email,
        @Size(min = 8, message = "The password must have at least 8 characters.")
        String password) {
}
