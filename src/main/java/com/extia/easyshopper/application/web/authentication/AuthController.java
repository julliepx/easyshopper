package com.extia.easyshopper.application.web.authentication;

import com.extia.easyshopper.application.dto.request.RegisterRequest;
import com.extia.easyshopper.application.dto.request.LoginRequest;
import com.extia.easyshopper.application.dto.response.AuthResponse;
import com.extia.easyshopper.domain.service.auth.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements IAuthController {
    @Autowired
    private IAuthService authService;

    public ResponseEntity<AuthResponse> login(LoginRequest request) {
        return new ResponseEntity<>(this.authService.login(request), HttpStatus.OK);
    }

    public ResponseEntity<AuthResponse> register(RegisterRequest request){
        return new ResponseEntity<>(this.authService.register(request), HttpStatus.CREATED);
    }
}
