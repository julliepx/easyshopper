package com.extia.easyshopper.application.web.authentication;

import com.extia.easyshopper.application.dto.request.LoginRequest;
import com.extia.easyshopper.application.dto.request.RegisterRequest;
import com.extia.easyshopper.application.dto.response.AuthResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAuthController {
    ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest request);
    ResponseEntity<AuthResponse> register(@RequestBody @Valid RegisterRequest request);
}
