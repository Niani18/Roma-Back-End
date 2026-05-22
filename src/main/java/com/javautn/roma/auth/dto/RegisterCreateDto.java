package com.javautn.roma.auth.dto;

import jakarta.validation.constraints.NotBlank;

public class RegisterCreateDto {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String confirmPassword;
    private Long citizenId;

    protected RegisterCreateDto() {}

    public RegisterCreateDto(String username, String password, String confirmPassword, Long citizenId) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.citizenId = citizenId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Long getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(Long citizenId) {
        this.citizenId = citizenId;
    }
}
