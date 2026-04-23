package com.javautn.roma.human.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public class CitizenCreateDTO extends HumanCreateDTO {

    @NotBlank private final String socialRole;

    public CitizenCreateDTO(
            final String fullName,
            final Date birthDate,
            final Date deathDate,
            final String socialRole) {
        super(fullName, birthDate, deathDate);
        this.socialRole = socialRole;
    }

    public String getSocialRole() {
        return socialRole;
    }
}
