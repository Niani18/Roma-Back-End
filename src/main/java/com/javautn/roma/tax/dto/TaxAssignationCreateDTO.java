package com.javautn.roma.tax.dto;

import com.javautn.roma.tax.entity.TaxAssignationEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class TaxAssignationCreateDTO {


    @NotNull private long taxId;
    @NotNull private double amount;
    @NotBlank private Date expiryDate;
    private Date paymentDate;
    @NotBlank private String state;
    @NotNull private double interest;
    private String sanction;

    private long familyId = 0;
    private long propertyId = 0;


    public TaxAssignationCreateDTO(
            long taxId, double amount, Date expiryDate, Date paymentDate, String state,
            double interest, String sanction, long familyId, long propertyId) {
        this.taxId = taxId;
        this.amount = amount;
        this.expiryDate = expiryDate;
        this.paymentDate = paymentDate;
        this.state = state;
        this.interest = interest;
        this.sanction = sanction;
        this.familyId = familyId;
        this.propertyId = propertyId;
    }


    public long getTaxId() {
        return taxId;
    }

    public void setTaxId(long taxId) {
        this.taxId = taxId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public String getSanction() {
        return sanction;
    }

    public void setSanction(String sanction) {
        this.sanction = sanction;
    }

    public long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(long familyId) {
        this.familyId = familyId;
    }

    public long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(long propertyId) {
        this.propertyId = propertyId;
    }

    public TaxAssignationEntity newAssignation() {
        return new TaxAssignationEntity(
                this.amount,
                this.expiryDate,
                this.paymentDate,
                this.state,
                this.interest,
                this.sanction
        );
    }
}
