package com.javautn.roma.family.dto;

import com.javautn.roma.familyRol.entity.FamilyRolEntity;
import com.javautn.roma.familyRol.entity.Role;
import com.javautn.roma.human.dto.CitizenResponseDTO;

public class FamilyMemberDto {

    private Role role;
    private CitizenResponseDTO citizen;

    protected FamilyMemberDto() {}

    public FamilyMemberDto(Role role, CitizenResponseDTO citizen) {
        this.role = role;
        this.citizen = citizen;
    }

    public Role getRole() {
        return role;
    }

    public CitizenResponseDTO getCitizen() {
        return citizen;
    }

    public static FamilyMemberDto fromFamilyRol(FamilyRolEntity familyRol) {
        return new FamilyMemberDto(
                familyRol.getRole(),
                CitizenResponseDTO.fromCitizen(familyRol.getCitizen())
        );
    }
}
