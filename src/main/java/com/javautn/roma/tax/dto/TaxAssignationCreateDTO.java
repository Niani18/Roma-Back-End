package com.javautn.roma.tax.dto;

import com.javautn.roma.tax.entity.TaxAssignationEntity;
import com.javautn.roma.tax.entity.StateAsignation;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class TaxAssignationCreateDTO {


    @NotNull private Long taxId;
    @NotNull private Double amount;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Argentina/Buenos_Aires")
    @NotNull private Date expiryDate;

    protected TaxAssignationCreateDTO() {}

    public TaxAssignationCreateDTO(
            Long taxId, Double amount, Date expiryDate) {
        this.taxId = taxId;
        this.amount = amount;
        this.expiryDate = expiryDate;
    }


    public Long getTaxId() {
        return taxId;
    }

    public void setTaxId(Long taxId) {
        this.taxId = taxId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }


}
