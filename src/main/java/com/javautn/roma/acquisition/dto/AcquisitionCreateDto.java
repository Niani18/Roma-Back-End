package com.javautn.roma.acquisition.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class AcquisitionCreateDto {

    @NotNull private Double price;
    private Date date;
    @NotNull private Long slave;
    @NotNull private Long family;

    public AcquisitionCreateDto(double price, long slave, long family) {
        this.price = price;
        this.slave = slave;
        this.family = family;
    }

    protected AcquisitionCreateDto() {}

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getFamily() {
        return family;
    }

    public void setFamily(long family) {
        this.family = family;
    }

    public long getSlave() {
        return slave;
    }

    public void setSlave(long slave) {
        this.slave = slave;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
