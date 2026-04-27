package com.javautn.roma.tax.dto;

import com.javautn.roma.tax.entity.TaxEntity;
import jakarta.validation.constraints.NotBlank;

public class TaxCreateDTO {
    @NotBlank final String name;
    @NotBlank final String description;

    public TaxCreateDTO(
            final String name,
            final String description) {
        this.name = name;
        this.description = description;
    }

    public TaxEntity newTax() {
        return new TaxEntity(
                this.name,
                this.description
        );
    }
}
