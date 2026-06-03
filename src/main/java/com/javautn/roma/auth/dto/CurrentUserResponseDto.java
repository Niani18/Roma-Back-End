package com.javautn.roma.auth.dto;

import com.javautn.roma.auth.entity.Role;
import com.javautn.roma.auth.entity.UserEntity;
import com.javautn.roma.auth.entity.UserState;

public class CurrentUserResponseDto {

    private Long userId;
    private String username;
    private Role role;
    private UserState state;
    private Long citizenId;

    public CurrentUserResponseDto(Long userId, String username, Role role, UserState state, Long citizenId) {
        this.userId = userId;
        this.username = username;
        this.role = role;
        this.state = state;
        this.citizenId = citizenId;
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

    public static CurrentUserResponseDto fromUser(UserEntity user) {
        return new CurrentUserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getRole(),
                user.getState(),
                user.getCitizenId()
        );
    }
}
