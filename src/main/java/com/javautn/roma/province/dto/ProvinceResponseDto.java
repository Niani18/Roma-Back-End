package com.javautn.roma.province.dto;

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

}
