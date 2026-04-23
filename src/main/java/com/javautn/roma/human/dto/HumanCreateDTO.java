package com.javautn.roma.human.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public class HumanCreateDTO {
    @NotBlank final String fullName;
    @NotNull private final Date birthDate;
    private final Date deathDate;

    public HumanCreateDTO(final String fullName, final Date birthDate, final Date deathDate) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
    }

    public String getFullName() {
        return fullName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Date getDeathDate() {
        return deathDate;
    }
}
