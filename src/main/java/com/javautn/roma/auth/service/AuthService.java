package com.javautn.roma.auth.service;

import com.javautn.roma.auth.dto.CurrentUserResponseDto;
import com.javautn.roma.auth.dto.LoginRequestDto;
import com.javautn.roma.auth.dto.LoginResponseDto;
import com.javautn.roma.auth.dto.RegisterCreateDto;
import com.javautn.roma.auth.dto.RegisterResponseDto;
import com.javautn.roma.auth.entity.Role;
import com.javautn.roma.auth.entity.UserEntity;
import com.javautn.roma.auth.repository.UserRepository;
import com.javautn.roma.human.entity.CitizenEntity;
import com.javautn.roma.human.service.HumanService;
import com.javautn.roma.shared.exception.UnauthorizedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;
    private final HumanService humanService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtEncoder jwtEncoder,
            HumanService humanService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtEncoder = jwtEncoder;
        this.humanService = humanService;
    }

    @Transactional(readOnly = true)
    public LoginResponseDto login(LoginRequestDto dto) {
        UserEntity user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new UnauthorizedException("Credenciales inválidas"));

        boolean passwordMatches = passwordEncoder.matches(
                dto.getPassword(),
                user.getPassword()
        );

        if (!passwordMatches) {
            throw new UnauthorizedException("Credenciales inválidas");
        }

        String token = generateToken(user);

        return LoginResponseDto.fromUser(token, user);
    }

    public RegisterResponseDto register(RegisterCreateDto dto) {
        CitizenEntity citizen = humanService.getCitizen(dto.getCitizenId());
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new UnauthorizedException("Passwords do not match");
        }

        UserEntity newUser = userRepository.save(new UserEntity(
                dto.getUsername(),
                passwordEncoder.encode(dto.getPassword()),
                Role.USER,
                citizen
        ));

        return RegisterResponseDto.fromResponseDto(newUser);

    }

    @Transactional(readOnly = true)
    public CurrentUserResponseDto me() {
        return CurrentUserResponseDto.fromUser(currentUser());
    }

    private String generateToken(UserEntity user) {
        Instant now = Instant.now();

        JwtClaimsSet.Builder claimsBuilder = JwtClaimsSet.builder()
                .subject(user.getId().toString())
                .issuedAt(now)
                .expiresAt(now.plus(2, ChronoUnit.HOURS))
                .claim("username", user.getUsername())
                .claim("roles", List.of(user.getRole().name()));

        if (user.getCitizenId() != null) {
            claimsBuilder.claim("citizenId", user.getCitizenId());
        }

        JwtClaimsSet claims = claimsBuilder.build();

        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();

        return jwtEncoder.encode(
                JwtEncoderParameters.from(header, claims)
        ).getTokenValue();
    }

    private UserEntity currentUser() {
        String userId = Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getName();
        return userRepository.findById(Long.parseLong(userId))
                .orElseThrow(() -> new UnauthorizedException("Usuario autenticado inexistente"));
    }
}
