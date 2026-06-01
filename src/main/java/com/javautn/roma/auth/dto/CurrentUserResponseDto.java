package com.javautn.roma.auth.dto;

import com.javautn.roma.auth.entity.Role;
import com.javautn.roma.auth.entity.UserEntity;

public class CurrentUserResponseDto {

    private Long userId;
    private String username;
    private Role role;
    private Long citizenId;

    public CurrentUserResponseDto(Long userId, String username, Role role, Long citizenId) {
        this.userId = userId;
        this.username = username;
        this.role = role;
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

    public Long getCitizenId() {
        return citizenId;
    }

    public static CurrentUserResponseDto fromUser(UserEntity user) {
        return new CurrentUserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getRole(),
                user.getCitizenId()
        );
    }
}
