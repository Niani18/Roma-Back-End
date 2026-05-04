package com.javautn.roma.family.dto;

import com.javautn.roma.family.entity.FamilyEntity;
import com.javautn.roma.province.dto.ProvinceResponseDto;

import java.util.List;

public class FamilyWithMembersDto {

    private long id;
    private String name;
    private ProvinceResponseDto province;
    private List<FamilyMemberDto> members;

    protected FamilyWithMembersDto() {}

    public FamilyWithMembersDto(long id, String name, ProvinceResponseDto province, List<FamilyMemberDto> members) {
        this.id = id;
        this.name = name;
        this.province = province;
        this.members = members;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ProvinceResponseDto getProvince() {
        return province;
    }

    public List<FamilyMemberDto> getMembers() {
        return members;
    }

    public static FamilyWithMembersDto fromFamily(FamilyEntity family) {
        return new FamilyWithMembersDto(
                family.getId(),
                family.getName(),
                ProvinceResponseDto.fromProvince(family.getProvince()),
                family.getFamilyRol().stream()
                        .map(FamilyMemberDto::fromFamilyRol)
                        .toList()
        );
    }
}
