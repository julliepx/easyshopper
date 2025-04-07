package com.extia.easyshopper.domain.service.auth;

import com.extia.easyshopper.application.dto.request.LoginRequest;
import com.extia.easyshopper.application.dto.request.RegisterRequest;
import com.extia.easyshopper.application.dto.response.AuthResponse;
import com.extia.easyshopper.domain.exception.BadRequestException;
import com.extia.easyshopper.domain.exception.NotFoundException;
import com.extia.easyshopper.domain.model.User;
import com.extia.easyshopper.infrastructure.config.security.TokenService;
import com.extia.easyshopper.infrastructure.repository.user.IUserRepository;
import com.extia.easyshopper.stubs.IAuthStub;
import com.extia.easyshopper.stubs.IUserStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    @InjectMocks
    private AuthService authService;
    @Mock
    private IUserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private TokenService tokenService;

    private User user;
    private String token;
    private LoginRequest loginRequest;
    private RegisterRequest registerRequest;

    @BeforeEach
    public void setup() {
        this.user = IUserStub.buildUser();
        this.token = this.tokenService.generateToken(user);
        this.loginRequest = IAuthStub.buildLoginRequest();
        this.registerRequest = IAuthStub.buildRegisterRequest();
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenUserNonExistent() {
        when(this.userRepository.findByEmail(loginRequest.email())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> this.authService.login(loginRequest));
        verify(this.userRepository).findByEmail(loginRequest.email());
    }

    @Test
    public void shouldThrowBadRequestExceptionWhenPasswordDoesNotMatch() {
        when(this.userRepository.findByEmail(loginRequest.email())).thenReturn(Optional.of(user));
        when(this.passwordEncoder.matches(loginRequest.password(), "12345678")).thenReturn(false);

        assertThrows(BadRequestException.class, () -> this.authService.login(loginRequest));
    }

    @Test
    public void shouldLoginSuccessfully() {
        when(this.userRepository.findByEmail(loginRequest.email())).thenReturn(Optional.of(user));
        when(this.passwordEncoder.matches(loginRequest.password(), "12345678")).thenReturn(true);
        when(this.tokenService.generateToken(user)).thenReturn(token);

        AuthResponse response = this.authService.login(loginRequest);

        assertAll("loginSuccessfully",
                () -> assertEquals(response.name(), user.getName()),
                () -> assertEquals(response.token(), token));
    }

    @Test
    public void shouldThrowBadRequestExceptionWhenEmailAlreadyRegistered() {
        when(this.userRepository.findByEmail(registerRequest.email())).thenReturn(Optional.of(user));

        assertThrows(BadRequestException.class, () -> this.authService.register(registerRequest));
        verify(this.userRepository).findByEmail(registerRequest.email());
    }

    @Test
    public void shouldRegisterSuccessfully() {
        when(this.userRepository.findByEmail(registerRequest.email())).thenReturn(Optional.empty());
        when(this.tokenService.generateToken(any(User.class))).thenReturn(token);

        AuthResponse response = this.authService.register(registerRequest);

        assertAll("registerSuccessfully",
                () -> assertEquals(response.name(), registerRequest.name()),
                () -> assertEquals(response.token(), token));
        verify(this.userRepository).findByEmail(registerRequest.email());
        verify(this.userRepository).save(any(User.class));
    }
}
