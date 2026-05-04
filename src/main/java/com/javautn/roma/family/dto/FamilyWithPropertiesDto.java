package com.javautn.roma.family.dto;

import com.javautn.roma.property.dto.PropertyResponseDto;
import com.javautn.roma.province.dto.ProvinceResponseDto;

import java.util.List;

public class FamilyWithPropertiesDto {

    private long id;
    private String name;
    private ProvinceResponseDto province;
    private List<PropertyResponseDto> properties;

    protected FamilyWithPropertiesDto() {}

    public FamilyWithPropertiesDto(long id, String name, ProvinceResponseDto province, List<PropertyResponseDto> properties) {
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

    public List<PropertyResponseDto> getProperties() {
        return properties;
    }
}
