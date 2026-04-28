package com.javautn.roma.crime.dto;

import jakarta.validation.constraints.NotBlank;


public class CrimeCreateDto {

    @NotBlank
    private String description;

    public CrimeCreateDto(String description) {this.description = description;}

    public CrimeCreateDto() {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
