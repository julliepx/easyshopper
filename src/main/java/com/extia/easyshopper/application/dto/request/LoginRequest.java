package com.extia.easyshopper.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record LoginRequest(
        @Email(message = "The value must be a valid e-mail address.")
        @NotBlank(message = "The e-mail must not be blank.")
        String email,
        @Size(min = 8, message = "The password must have at least 8 characters.")
        String password) {
}
