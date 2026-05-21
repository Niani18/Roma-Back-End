package com.javautn.roma.human.dto;

import com.javautn.roma.human.entity.CitizenEntity;

import java.util.Date;
import java.util.List;

public class CitizenWithLegalCasesDto {

    private long id;
    private String fullName;
    private Date birthDate;
    private Date deathDate;
    private String socialRole;
    private List<CitizenLegalCaseDto> legalCases;

    public CitizenWithLegalCasesDto(long id, String fullName, Date birthDate, Date deathDate,
                                    String socialRole, List<CitizenLegalCaseDto> legalCases) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.socialRole = socialRole;
        this.legalCases = legalCases;
    }

    public long getId() {
        return id;
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

    public String getSocialRole() {
        return socialRole;
    }

    public List<CitizenLegalCaseDto> getLegalCases() {
        return legalCases;
    }

    public static CitizenWithLegalCasesDto fromCitizen(CitizenEntity citizen) {
        return new CitizenWithLegalCasesDto(
                citizen.getId(),
                citizen.getFullName(),
                citizen.getBirthDate(),
                citizen.getDeathDate(),
                citizen.getSocialRole(),
                citizen.getLegalCases().stream().map(CitizenLegalCaseDto::fromLegalCase).toList()
        );
    }
}
