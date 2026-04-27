package com.javautn.roma.tax.dto;


import com.javautn.roma.tax.entity.TaxEntity;

public class TaxResponseDTO {
    final long id;
    final String name;
    final String description;

    public TaxResponseDTO(long id, String name, String description) {
        this.id = id;
        this.name = name;
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
