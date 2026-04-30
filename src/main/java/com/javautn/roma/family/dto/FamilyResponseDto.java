package com.javautn.roma.family.dto;

import com.javautn.roma.province.dto.ProvinceResponseDto;

public class FamilyResponseDto {

    private long id;
    private String name;
    private ProvinceResponseDto province;

    public FamilyResponseDto(long id, String name,  ProvinceResponseDto province) {
        this.id = id;
        this.name = name;
        this.province = province;
    }

    protected FamilyResponseDto() {}

    public ProvinceResponseDto getProvince() {
        return province;
    }

    public void setProvince(ProvinceResponseDto province) {}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
