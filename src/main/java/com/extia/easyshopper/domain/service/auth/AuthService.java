package com.extia.easyshopper.domain.service.auth;

import com.extia.easyshopper.application.dto.request.LoginRequest;
import com.extia.easyshopper.application.dto.request.RegisterRequest;
import com.extia.easyshopper.application.dto.response.AuthResponse;
import com.extia.easyshopper.domain.exception.BadRequestException;
import com.extia.easyshopper.domain.exception.NotFoundException;
import com.extia.easyshopper.domain.model.User;
import com.extia.easyshopper.infrastructure.config.security.TokenService;
import com.extia.easyshopper.infrastructure.repository.user.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthResponse login(LoginRequest request) {
        User user = this.userRepository.findByEmail(request.email())
                .orElseThrow(() -> new NotFoundException("The user was not found."));

        if(!this.passwordEncoder.matches(request.password(), user.getPassword()))
            throw new BadRequestException("The password given does not match.");

        String token =  this.tokenService.generateToken(user);
        return new AuthResponse(user.getName(), token);
    }

    public AuthResponse register(RegisterRequest request) {
        Optional<User> user = this.userRepository.findByEmail(request.email());

        if(user.isPresent()) throw new BadRequestException("The email provided is already in use.");

        User newUser = new User();
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setEmail(request.email());
        newUser.setName(request.name());
        this.userRepository.save(newUser);

        String token = this.tokenService.generateToken(newUser);
        return new AuthResponse(newUser.getName(), token);
    }
}
