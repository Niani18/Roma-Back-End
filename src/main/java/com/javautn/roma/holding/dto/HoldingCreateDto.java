package com.javautn.roma.holding.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class HoldingCreateDto {

    @NotNull private double price;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Argentina/Buenos_Aires")
    private Date date;
    @NotNull private long propertyId;
    @NotNull private long familyId;

    protected HoldingCreateDto() {}

    public HoldingCreateDto(double price, long propertyId, long familyId) {
        this.price = price;
        this.propertyId = propertyId;
        this.familyId = familyId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(long propertyId) {
        this.propertyId = propertyId;
    }

    public long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(long familyId) {
        this.familyId = familyId;
    }

}
