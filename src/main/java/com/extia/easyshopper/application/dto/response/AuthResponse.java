package com.extia.easyshopper.application.dto.response;

import lombok.Builder;

@Builder
public record AuthResponse(String name, String token) {
}
