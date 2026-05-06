package com.javautn.roma.tax.dto;

import com.javautn.roma.tax.entity.TaxEntity;
import jakarta.validation.constraints.NotBlank;

public class TaxCreateDTO {
    @NotBlank private String name;
    @NotBlank private String description;

    public TaxCreateDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    protected TaxCreateDTO() {}

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

    public TaxEntity newTax() {
        return new TaxEntity(
                this.name,
                this.description
        );
    }
}
