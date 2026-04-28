package com.javautn.roma.province.dto;

import com.javautn.roma.province.entity.ProvinceEntity;

public class ProvinceResponseDto {

    private long id;
    private String name;

    public ProvinceResponseDto() {}
    public ProvinceResponseDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static ProvinceResponseDto fromProvince (ProvinceEntity province) {
        return new ProvinceResponseDto(province.getId(), province.getName());
    }
}
