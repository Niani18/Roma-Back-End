package com.javautn.roma.holding.dto;

import com.javautn.roma.family.dto.FamilyResponseDto;
import com.javautn.roma.property.dto.PropertyResponseDto;

import java.util.Date;

public class HoldingResponseDto {

    private long id;
    private PropertyResponseDto property;
    private FamilyResponseDto family;
    private double price;
    private Date date;

    protected  HoldingResponseDto() {}

    public HoldingResponseDto(long id,  PropertyResponseDto property, FamilyResponseDto family, double price, Date date) {
        this.id = id;
        this.property = property;
        this.family = family;
        this.price = price;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PropertyResponseDto getProperty() {
        return property;
    }

    public void setProperty(PropertyResponseDto property) {
        this.property = property;
    }

    public FamilyResponseDto getFamily() {
        return family;
    }

    public void setFamily(FamilyResponseDto family) {
        this.family = family;
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
