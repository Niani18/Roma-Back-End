package com.javautn.roma.property.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PropertyCreateDto {

    @NotBlank private String name;
    @NotBlank private String description;

    public PropertyCreateDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    protected PropertyCreateDto() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
