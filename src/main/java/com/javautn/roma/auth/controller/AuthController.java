package com.javautn.roma.auth.controller;

import com.javautn.roma.auth.dto.CurrentUserResponseDto;
import com.javautn.roma.auth.dto.LoginRequestDto;
import com.javautn.roma.auth.dto.LoginResponseDto;
import com.javautn.roma.auth.dto.RegisterCreateDto;
import com.javautn.roma.auth.dto.RegisterResponseDto;
import com.javautn.roma.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(final AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @Valid @RequestBody final LoginRequestDto dto
    ) {
        return ResponseEntity.ok(authService.login(dto));
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RegisterResponseDto> register(@Valid @RequestBody final RegisterCreateDto dto){
        return ResponseEntity.ok(authService.register(dto));
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CurrentUserResponseDto> me() {
        return ResponseEntity.ok(authService.me());
    }
}
