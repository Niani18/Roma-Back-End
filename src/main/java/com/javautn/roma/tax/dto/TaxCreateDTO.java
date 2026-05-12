package com.javautn.roma.tax.dto;

import com.javautn.roma.tax.entity.Target;
import com.javautn.roma.tax.entity.TaxEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TaxCreateDTO {
    @NotBlank private String name;
    @NotBlank private String description;
    @NotNull private Target target;

    public TaxCreateDTO(String name, String description,  Target target) {
        this.name = name;
        this.description = description;
        this.target = target;
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

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public TaxEntity newTax() {
        return new TaxEntity(
                this.name,
                this.description,
                this.target
        );
    }
}
