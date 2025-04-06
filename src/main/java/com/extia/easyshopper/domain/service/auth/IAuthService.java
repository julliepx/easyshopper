package com.extia.easyshopper.domain.service.auth;

import com.extia.easyshopper.application.dto.request.LoginRequest;
import com.extia.easyshopper.application.dto.request.RegisterRequest;
import com.extia.easyshopper.application.dto.response.AuthResponse;
import org.springframework.http.ResponseEntity;

public interface IAuthService {
    ResponseEntity<AuthResponse> login(LoginRequest request);
    ResponseEntity<AuthResponse> register(RegisterRequest request);
}
