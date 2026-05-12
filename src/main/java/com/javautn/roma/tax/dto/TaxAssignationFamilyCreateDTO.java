package com.javautn.roma.tax.dto;

import com.javautn.roma.tax.entity.TaxAssignationEntity;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class TaxAssignationFamilyCreateDTO extends TaxAssignationCreateDTO {

    @NotNull private Long familyId;

    protected TaxAssignationFamilyCreateDTO() {}

    public TaxAssignationFamilyCreateDTO(Long taxId, Double amount, Date expiryDate, Long familyId) {
        super(taxId, amount, expiryDate);
        this.familyId = familyId;
    }

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }



}
