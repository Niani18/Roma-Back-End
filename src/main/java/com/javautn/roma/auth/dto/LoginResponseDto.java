package com.javautn.roma.auth.dto;

import com.javautn.roma.auth.entity.Role;
import com.javautn.roma.auth.entity.UserEntity;
import com.javautn.roma.auth.entity.UserState;

public class LoginResponseDto {

    private String token;
    private Long userId;
    private String username;
    private Role role;
    private UserState state;
    private Long citizenId;

    public LoginResponseDto(String token, Long userId, String username, Role role, UserState state, Long citizenId) {
        this.token = token;
        this.userId = userId;
        this.username = username;
        this.role = role;
        this.state = state;
        this.citizenId = citizenId;
    }

    public String getToken() {
        return token;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }

    public UserState getState() {
        return state;
    }

    public Long getCitizenId() {
        return citizenId;
    }

    public static LoginResponseDto fromUser(String token, UserEntity user) {
        return new LoginResponseDto(
                token,
                user.getId(),
                user.getUsername(),
                user.getRole(),
                user.getState(),
                user.getCitizenId()
        );
    }
}
