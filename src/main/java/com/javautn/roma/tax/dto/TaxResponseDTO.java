package com.javautn.roma.tax.dto;


import com.javautn.roma.tax.entity.TaxEntity;

public class TaxResponseDTO {
    private long id;
    private String name;
    private String description;

    public TaxResponseDTO(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public static TaxResponseDTO fromTax(final TaxEntity tax) {
        if (tax == null) return null;
        return new TaxResponseDTO(
                tax.getId(),
                tax.getName(),
                tax.getDescription()
        );
    }


}
