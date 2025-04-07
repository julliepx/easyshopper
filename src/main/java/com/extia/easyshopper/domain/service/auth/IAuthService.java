package com.extia.easyshopper.domain.service.auth;

import com.extia.easyshopper.application.dto.request.LoginRequest;
import com.extia.easyshopper.application.dto.request.RegisterRequest;
import com.extia.easyshopper.application.dto.response.AuthResponse;

public interface IAuthService {
    AuthResponse login(LoginRequest request);
    AuthResponse register(RegisterRequest request);
}
