package com.javautn.roma.familyRol.dto;

import com.javautn.roma.family.dto.FamilyResponseDto;
import com.javautn.roma.familyRol.entity.Role;
import com.javautn.roma.human.dto.CitizenResponseDTO;

import java.util.Date;

public class FamilyRolResponseDto {

    private long id;
    private String rolName;
    private CitizenResponseDTO citizen;
    private FamilyResponseDto family;
    private Date joingDate;
    private Date unjoingDate;

    protected FamilyRolResponseDto() {}

    public FamilyRolResponseDto (long id, Role role, CitizenResponseDTO citizen, FamilyResponseDto family, Date joingDate, Date unjoingDate) {
        this.id = id;
        this.rolName = role.toString();
        this.citizen = citizen;
        this.family = family;
        this.joingDate = joingDate;
        this.unjoingDate = unjoingDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRolName() {
        return rolName;
    }

    public void setRolName(String rolName) {
        this.rolName = rolName;
    }

    public CitizenResponseDTO getCitizen() {
        return citizen;
    }

    public void setCitizen(CitizenResponseDTO citizen) {
        this.citizen = citizen;
    }

    public FamilyResponseDto getFamily() {
        return family;
    }

    public void setFamily(FamilyResponseDto family) {
        this.family = family;
    }

    public Date getJoingDate() {
        return joingDate;
    }

    public void setJoingDate(Date joingDate) {
        this.joingDate = joingDate;
    }

    public Date getUnjoingDate() {
        return unjoingDate;
    }

    public void setUnjoingDate(Date unjoingDate) {
        this.unjoingDate = unjoingDate;
    }
}
