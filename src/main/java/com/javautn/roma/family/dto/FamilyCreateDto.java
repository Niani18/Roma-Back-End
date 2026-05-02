package com.javautn.roma.family.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FamilyCreateDto {

    @NotBlank
    private String name;

    @NotNull
    private Long provinceId;

    protected FamilyCreateDto() {}

    public FamilyCreateDto(String name, long provinceId) {
        this.name = name;
        this.provinceId = provinceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

}
