package com.javautn.roma.family.dto;

import com.javautn.roma.family.entity.FamilyEntity;
import com.javautn.roma.holding.dto.HoldingResponseDto;
import com.javautn.roma.province.dto.ProvinceResponseDto;

import java.util.List;

public class FamilyWithPropertiesDto {

    private long id;
    private String name;
    private ProvinceResponseDto province;
    private List<HoldingResponseDto> properties;

    protected FamilyWithPropertiesDto() {}

    public FamilyWithPropertiesDto(long id, String name, ProvinceResponseDto province, List<HoldingResponseDto> properties) {
        this.id = id;
        this.name = name;
        this.province = province;
        this.properties = properties;
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

    public List<HoldingResponseDto> getPropertyHoldings() {
        return properties;
    }

    public static FamilyWithPropertiesDto fromFamily(FamilyEntity family) {
        return new FamilyWithPropertiesDto(
                family.getId(),
                family.getName(),
                ProvinceResponseDto.fromProvince(family.getProvince()),
                family.getHoldings().stream().map(HoldingResponseDto::fromHolding).toList()
        );
    }
}
