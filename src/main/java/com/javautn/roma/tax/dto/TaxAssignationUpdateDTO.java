package com.javautn.roma.tax.dto;

import java.util.Date;
import java.util.Optional;

public class TaxAssignationUpdateDTO {

    private Optional<Double> amount;
    private Optional<Date> expiryDate;
    private Optional<Date> paymentDate;
    private Optional<Double> interest;
    private Optional<String> sanction;

    public TaxAssignationUpdateDTO() {
        this.amount = Optional.empty();
        this.expiryDate = Optional.empty();
        this.paymentDate = Optional.empty();
        this.interest = Optional.empty();
    }

    public Optional<Double> getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = Optional.of(amount);
    }

    public Optional<Date> getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = Optional.ofNullable(expiryDate);
    }

    public Optional<Date> getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = Optional.ofNullable(paymentDate);
    }

    public Optional<Double> getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = Optional.of(interest);
    }

    public Optional<String> getSanction() {
        return sanction;
    }

    public void setSanction(String sanction) {
        this.sanction = Optional.ofNullable(sanction);
    }
}
