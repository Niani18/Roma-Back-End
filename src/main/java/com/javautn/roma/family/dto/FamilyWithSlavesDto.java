package com.javautn.roma.family.dto;

import com.javautn.roma.family.entity.FamilyEntity;
import com.javautn.roma.province.dto.ProvinceResponseDto;

import java.util.List;

public class FamilyWithSlavesDto {

    private long id;
    private String name;
    private ProvinceResponseDto province;
    private List<FamilySlavesDto> slaves;

    protected FamilyWithSlavesDto() {}

    public FamilyWithSlavesDto(long id, String name, ProvinceResponseDto province, List<FamilySlavesDto> slaves) {
        this.id = id;
        this.name = name;
        this.province = province;
        this.slaves = slaves;
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

    public List<FamilySlavesDto> getSlaves() {
        return slaves;
    }

    public static FamilyWithSlavesDto fromFamily(FamilyEntity family) {
        return new FamilyWithSlavesDto(
                family.getId(),
                family.getName(),
                ProvinceResponseDto.fromProvince(family.getProvince()),
                family.getAcquisitions().stream()
                        .map(FamilySlavesDto::fromEntity)
                        .toList()
        );
    }

}
