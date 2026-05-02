package com.javautn.roma.human.dto;

import com.javautn.roma.human.entity.CitizenEntity;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public class CitizenCreateDTO extends HumanCreateDTO {

    @NotBlank private String socialRole;

    protected CitizenCreateDTO() {}

    public CitizenCreateDTO(
            final String fullName,
            final Date birthDate,
            final String socialRole) {
        super(fullName, birthDate);
        this.socialRole = socialRole;
    }

    public String getSocialRole() {
        return socialRole;
    }

    public void setSocialRole(String socialRole) {
        this.socialRole = socialRole;
    }

    public CitizenEntity newCitizen() {
        return new CitizenEntity(
                super.getFullName(),
                super.getBirthDate(),
                this.socialRole
        );
    }
}
