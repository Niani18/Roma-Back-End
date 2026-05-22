package com.javautn.roma.auth.dto;

import com.javautn.roma.auth.entity.UserEntity;
import com.javautn.roma.human.dto.CitizenResponseDTO;

public class RegisterResponseDto {

    private Long id;
    private String username;
    private CitizenResponseDTO citizen;

    protected RegisterResponseDto() {}

    public RegisterResponseDto(Long id, String username, CitizenResponseDTO citizen) {
        this.id = id;
        this.username = username;
        this.citizen = citizen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public CitizenResponseDTO getCitizen() {
        return citizen;
    }

    public void setCitizen(CitizenResponseDTO citizen) {
        this.citizen = citizen;
    }

    public static RegisterResponseDto fromResponseDto(UserEntity dto) {
        return new RegisterResponseDto(
                dto.getId(),
                dto.getUsername(),
                CitizenResponseDTO.fromCitizen(dto.getCitizen())
        );
    }
}
