package com.extia.easyshopper.application.web.authentication;

import com.extia.easyshopper.application.dto.request.RegisterRequest;
import com.extia.easyshopper.application.dto.request.LoginRequest;
import com.extia.easyshopper.domain.service.auth.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements IAuthController {
    @Autowired
    private IAuthService authService;

    public ResponseEntity login(LoginRequest request) {
        return this.authService.login(request);
    }

    public ResponseEntity register(RegisterRequest request){
        return this.authService.register(request);
    }
}
