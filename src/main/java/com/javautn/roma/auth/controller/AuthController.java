package com.javautn.roma.auth.controller;

import com.javautn.roma.auth.dto.LoginRequestDto;
import com.javautn.roma.auth.dto.LoginResponseDto;
import com.javautn.roma.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
}