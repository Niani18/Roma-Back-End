package com.javautn.roma.tax.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class TaxAssignationPropertyCreateDTO extends TaxAssignationCreateDTO {

    @NotNull private Long propertyId;

    protected TaxAssignationPropertyCreateDTO() {}

    public TaxAssignationPropertyCreateDTO(Long taxId, Double amount, Date expiryDate, Long propertyId) {
        super(taxId, amount, expiryDate);
        this.propertyId = propertyId;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }
}
