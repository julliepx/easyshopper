package com.extia.easyshopper.domain.handler;

import com.extia.easyshopper.application.dto.response.ExceptionResponse;
import com.extia.easyshopper.domain.exception.BadRequestException;
import com.extia.easyshopper.domain.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException exception) {
        return new ResponseEntity<>(ExceptionResponse.builder()
                .title(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(exception.getMessage())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .dateTime(LocalDateTime.now())
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionResponse> handleBadRequestException(BadRequestException exception) {
        return new ResponseEntity<>(ExceptionResponse.builder()
                .title(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(exception.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .dateTime(LocalDateTime.now())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .findFirst()
                .orElse("Validation failed");

        return new ResponseEntity<>(ExceptionResponse.builder()
                .title(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(message)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .dateTime(LocalDateTime.now())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ExceptionResponse> handleHandlerMethodValidation(HandlerMethodValidationException exception) {
        String message = exception.getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .findFirst()
                .orElse("Validation failed");

        return new ResponseEntity<>(ExceptionResponse.builder()
                .title(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(message)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .dateTime(LocalDateTime.now())
                .build(), HttpStatus.BAD_REQUEST);
    }
}