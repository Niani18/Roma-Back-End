package com.javautn.roma.province.dto;

import jakarta.validation.constraints.NotBlank;

public class ProvinceCreateDto {

    @NotBlank
    private String name;

    public ProvinceCreateDto(String name) {
        this.name = name;
    }

    public ProvinceCreateDto() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
