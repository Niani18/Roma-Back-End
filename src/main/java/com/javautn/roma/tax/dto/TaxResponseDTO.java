package com.javautn.roma.tax.dto;


import com.javautn.roma.tax.entity.Target;
import com.javautn.roma.tax.entity.TaxEntity;

public class TaxResponseDTO {
    private long id;
    private String name;
    private String description;
    private Target target;

    public TaxResponseDTO(long id, String name, String description,  Target target) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.target = target;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public static TaxResponseDTO fromTax(final TaxEntity tax) {
        if (tax == null) return null;
        return new TaxResponseDTO(
                tax.getId(),
                tax.getName(),
                tax.getDescription(),
                tax.getTarget()
        );
    }


}
