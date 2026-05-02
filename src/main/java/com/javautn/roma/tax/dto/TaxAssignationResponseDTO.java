package com.javautn.roma.tax.dto;

import com.javautn.roma.tax.entity.TaxAssignationEntity;

import java.util.Date;

public class TaxAssignationResponseDTO {

    private double amount;
    private Date expiryDate;
    private Date paymentDate;
    private String state;
    private double interest;
    private String sanction;

    private long tax_id;
    private String tax_name;
    private String tax_description;

    public TaxAssignationResponseDTO(
            double amount, Date expiryDate, Date paymentDate, String state,
            double interest, String sanction, long tax_id,
            String tax_name, String tax_description) {
        this.amount = amount;
        this.expiryDate = expiryDate;
        this.paymentDate = paymentDate;
        this.state = state;
        this.interest = interest;
        this.sanction = sanction;
        this.tax_id = tax_id;
        this.tax_name = tax_name;
        this.tax_description = tax_description;
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

    public long getTaxId() {
        return tax_id;
    }

    public void setTaxId(long tax_id) {
        this.tax_id = tax_id;
    }

    public String getTaxName() {
        return tax_name;
    }

    public void setTaxName(String tax_name) {
        this.tax_name = tax_name;
    }

    public String getTaxDescription() {
        return tax_description;
    }

    public void setTaxDescription(String tax_description) {
        this.tax_description = tax_description;
    }

    public static TaxAssignationResponseDTO fromTaxAssignation(TaxAssignationEntity assignation) {
        return new TaxAssignationResponseDTO(
                assignation.getAmount(),
                assignation.getExpiryDate(),
                assignation.getPaymentDate(),
                assignation.getState(),
                assignation.getInterest(),
                assignation.getSanction(),
                assignation.getTax().getId(),
                assignation.getTax().getName(),
                assignation.getTax().getDescription()
        );
    }
}
