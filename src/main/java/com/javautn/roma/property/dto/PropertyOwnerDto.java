package com.javautn.roma.property.dto;

import java.util.Date;

public class PropertyOwnerDto {

    private long holdingId;
    private long familyId;
    private String familyName;
    private double price;
    private Date date;

    protected PropertyOwnerDto() {}

    public PropertyOwnerDto(long holdingId, long familyId, String familyName, double price, Date date) {
        this.holdingId = holdingId;
        this.familyId = familyId;
        this.familyName = familyName;
        this.price = price;
        this.date = date;
    }

    public long getHoldingId() {
        return holdingId;
    }

    public void setHoldingId(long holdingId) {
        this.holdingId = holdingId;
    }

    public long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(long familyId) {
        this.familyId = familyId;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
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
}
