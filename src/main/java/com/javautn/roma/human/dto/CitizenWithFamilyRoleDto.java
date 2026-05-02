package com.javautn.roma.human.dto;

import com.javautn.roma.human.entity.CitizenEntity;


import java.util.Date;
import java.util.List;

public class CitizenWithFamilyRoleDto {

    private long id;
    private String fullName;
    private Date birthDate;
    private Date deathDate;
    private String socialRole;
    private final List<CitizenFamilyRoleDto>  citizenFamilyRoles ;


    public CitizenWithFamilyRoleDto(long id, String fullName, Date birthDate, Date deathDate,
                              String socialRole,  List<CitizenFamilyRoleDto> citizenFamilyRoles) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.socialRole = socialRole;
        this.citizenFamilyRoles = citizenFamilyRoles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }

    public String getSocialRole() {
        return socialRole;
    }

    public void setSocialRole(String socialRole) {
        this.socialRole = socialRole;
    }

    public List<CitizenFamilyRoleDto> getCitizenFamilyRoles() {
        return citizenFamilyRoles;
    }

    public static CitizenWithFamilyRoleDto fromCitizenWith(CitizenEntity citizenEntity) {
        List<CitizenFamilyRoleDto> citizenFamilyRoles = citizenEntity.getFamilyRol().stream().
                map(CitizenFamilyRoleDto::fromCitizen).toList();

        return new CitizenWithFamilyRoleDto(
                citizenEntity.getId(),
                citizenEntity.getFullName(),
                citizenEntity.getBirthDate(),
                citizenEntity.getDeathDate(),
                citizenEntity.getSocialRole(),
                citizenFamilyRoles
        );
    }
}
