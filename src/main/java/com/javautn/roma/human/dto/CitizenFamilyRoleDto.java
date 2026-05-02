package com.javautn.roma.human.dto;

import com.javautn.roma.family.dto.FamilyResponseDto;
import com.javautn.roma.familyRol.entity.FamilyRolEntity;
import com.javautn.roma.familyRol.entity.Role;
import com.javautn.roma.human.entity.CitizenEntity;

import java.util.Date;

public class CitizenFamilyRoleDto {

    private FamilyResponseDto familyDto;
    private Role role;
    private Date joingDate;
    private Date unjoinDate;

    public CitizenFamilyRoleDto(Date unjoinDate, Date joingDate, Role role, FamilyResponseDto familyDto) {
        this.unjoinDate = unjoinDate;
        this.joingDate = joingDate;
        this.role = role;
        this.familyDto = familyDto;
    }

    protected CitizenFamilyRoleDto() {}

    public FamilyResponseDto getFamilyDto() {
        return familyDto;
    }

    public void setFamilyDto(FamilyResponseDto familyDto) {
        this.familyDto = familyDto;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getJoingDate() {
        return joingDate;
    }

    public void setJoingDate(Date joingDate) {
        this.joingDate = joingDate;
    }

    public Date getUnjoinDate() {
        return unjoinDate;
    }

    public void setUnjoinDate(Date unjoinDate) {
        this.unjoinDate = unjoinDate;
    }

    public static CitizenFamilyRoleDto fromCitizen (FamilyRolEntity citizen) {
        return new CitizenFamilyRoleDto(
                citizen.getDateOfUnjoining(),
                citizen.getDateOfJoining(),
                citizen.getRole(),
                FamilyResponseDto.fromFamily(citizen.getFamily())
                );
    }

}
