package com.javautn.roma.tax.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TaxAssignationUpdateDTO {

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Argentina/Buenos_Aires")
    private Date paymentDate;
    private Double interest;
    private String sanction;

    protected TaxAssignationUpdateDTO() {
    }

    public TaxAssignationUpdateDTO(Date paymentDate, Double interest, String sanction) {
        this.paymentDate = paymentDate == null ? null : new Date(paymentDate.getTime());
        this.interest = interest;
        this.sanction = sanction;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate == null ? null : new Date(paymentDate.getTime());
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public String getSanction() {
        return sanction;
    }

    public void setSanction(String sanction) {
        this.sanction = sanction;
    }
}
