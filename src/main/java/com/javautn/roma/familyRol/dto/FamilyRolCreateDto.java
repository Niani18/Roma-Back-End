package com.javautn.roma.familyRol.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javautn.roma.familyRol.entity.Role;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class FamilyRolCreateDto {

    @NotNull private Long citizenId;
    @NotNull private Long familyId;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Argentina/Buenos_Aires")
    private Date joingDate;
    @NotNull private Role rolName;

    protected FamilyRolCreateDto() {}

    public FamilyRolCreateDto(long citizenId, long familyId, Role rolName) {
        this.citizenId = citizenId;
        this.familyId = familyId;
        this.rolName = rolName;
        this.joingDate = new Date();
    }

    public Long getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(Long citizenId) {
        this.citizenId = citizenId;
    }

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }

    public Date getJoingDate() {
        return joingDate == null ? new Date() : joingDate;
    }

    public void setJoingDate(Date joingDate) {
        this.joingDate = joingDate;
    }

    public Role getRolName() {
        return rolName;
    }

    public void setRolName(Role rolName) {
        this.rolName = rolName;
    }
}
