package com.javautn.roma.human.dto;

import com.javautn.roma.human.entity.CitizenEntity;

import java.util.Date;

public class CitizenResponseDTO extends HumanResponseDTO {
    public final String socialRole;

    public CitizenResponseDTO(long id, String fullName, Date birthDate, Date deathDate,
                              String socialRole) {
        super(id, fullName, birthDate, deathDate);
        this.socialRole = socialRole;
    }

    public static CitizenResponseDTO fromCitizen(final CitizenEntity human) {
        return (human == null) ? null : new CitizenResponseDTO(
                human.getId(),
                human.getFullName(),
                human.getBirthDate(),
                human.getDeathDate(),
                human.getSocialRole()
        );
    }
}
