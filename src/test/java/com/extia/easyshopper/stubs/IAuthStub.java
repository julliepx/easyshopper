package com.extia.easyshopper.stubs;

import com.extia.easyshopper.application.dto.request.LoginRequest;
import com.extia.easyshopper.application.dto.request.RegisterRequest;
import com.extia.easyshopper.application.dto.response.AuthResponse;

public interface IAuthStub {
    static LoginRequest buildLoginRequest() {
        return LoginRequest.builder()
                .email("userone@email.com")
                .password("12345678")
                .build();
    }

    static LoginRequest buildWrongPasswordLoginRequest() {
        return LoginRequest.builder()
                .email("userone@email.com")
                .password("wrongpassword")
                .build();
    }

    static LoginRequest buildInexistentLoginRequest() {
        return LoginRequest.builder()
                .email("inexistent@email.com")
                .password("12345678")
                .build();
    }

    static RegisterRequest buildRegisterRequest() {
        return RegisterRequest.builder()
                .name("usernew")
                .email("usernew@email.com")
                .password("12345678")
                .build();
    }

    static RegisterRequest buildExistentUserRegisterRequest() {
        return RegisterRequest.builder()
                .name("userone")
                .email("userone@email.com")
                .password("12345678")
                .build();
    }
}
