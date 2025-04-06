package com.extia.easyshopper.application.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ExceptionResponse(
        String title,
        String message,
        Integer statusCode,
        LocalDateTime dateTime
) {
}
