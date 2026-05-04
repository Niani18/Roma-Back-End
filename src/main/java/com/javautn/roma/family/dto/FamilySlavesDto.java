package com.javautn.roma.family.dto;

import com.javautn.roma.acquisition.entity.AcquisitionEntity;
import com.javautn.roma.family.entity.FamilyEntity;
import com.javautn.roma.human.dto.SlaveResponseDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FamilySlavesDto {

    private long id;
    private double price;
    private Date dateOfAcquisition;
    private SlaveResponseDTO slave;

    public FamilySlavesDto(long id, double price, Date dateOfAcquisition, SlaveResponseDTO slave) {
        this.id = id;
        this.price = price;
        this.dateOfAcquisition = dateOfAcquisition;
        this.slave = slave;
    }

    public SlaveResponseDTO getSlave() {
        return slave;
    }

    public void setSlave(SlaveResponseDTO slave) {
        this.slave = slave;
    }

    public Date getDateOfAcquisition() {
        return dateOfAcquisition;
    }

    public void setDateOfAcquisition(Date dateOfAcquisition) {
        this.dateOfAcquisition = dateOfAcquisition;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static FamilySlavesDto fromEntity(AcquisitionEntity ae) {
        return new FamilySlavesDto(
                ae.getId(),
                ae.getPrice(),
                ae.getDate(),
                SlaveResponseDTO.fromSlave(ae.getSlave())
                );
    }
}
