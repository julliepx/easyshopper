package com.extia.easyshopper.application.web.authentication;

import com.extia.easyshopper.application.dto.request.RegisterRequest;
import com.extia.easyshopper.domain.model.User;
import com.extia.easyshopper.application.dto.request.LoginRequest;
import com.extia.easyshopper.application.dto.response.AuthResponse;
import com.extia.easyshopper.domain.exception.NotFoundException;
import com.extia.easyshopper.infrastructure.repository.user.IUserRepository;
import com.extia.easyshopper.infrastructure.config.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController implements IAuthController {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(LoginRequest request) {
        User user = this.userRepository.findByEmail(request.email()).orElseThrow(() -> new NotFoundException("User not found."));

        if(this.passwordEncoder.matches(request.password(), user.getPassword())) {
            String token =  this.tokenService.generateToken(user);
            return ResponseEntity.ok(new AuthResponse(user.getName(), token));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(RegisterRequest request){
        Optional<User> user = this.userRepository.findByEmail(request.email());

        if(user.isEmpty()) {
            User newUser = new User();
            newUser.setPassword(passwordEncoder.encode(request.password()));
            newUser.setEmail(request.email());
            newUser.setName(request.name());
            this.userRepository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new AuthResponse(newUser.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}
