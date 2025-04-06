package com.extia.easyshopper.application.web.authentication;

import com.extia.easyshopper.application.dto.request.LoginRequest;
import com.extia.easyshopper.application.dto.request.RegisterRequest;
import com.extia.easyshopper.application.dto.response.AuthResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Authentication", description = "Operations to handle the API authentication")
@RequestMapping("/auth")
public interface IAuthController {
    @Operation(summary = "Login in the application", description = "Authenticate the user in the application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logged in successfully!"),
            @ApiResponse(responseCode = "400", description = "The password given does not match."),
            @ApiResponse(responseCode = "404", description = "The user was not found."),
            @ApiResponse(responseCode = "500", description = "An unexpected error ocurred while trying to login.")
    })
    @PostMapping("/login")
    ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest request);
    @Operation(summary = "Register in the application", description = "Register a new user in the application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registered successfully!"),
            @ApiResponse(responseCode = "400", description = "The email provided is already in use."),
            @ApiResponse(responseCode = "500", description = "An unexpected error ocurred while trying to register.")
    })
    @PostMapping("/register")
    ResponseEntity<AuthResponse> register(@RequestBody @Valid RegisterRequest request);
}
