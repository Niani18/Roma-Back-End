package com.javautn.roma.acquisition.dto;

import com.javautn.roma.acquisition.entity.AcquisitionEntity;
import com.javautn.roma.family.dto.FamilyResponseDto;
import com.javautn.roma.human.dto.SlaveResponseDTO;
import com.javautn.roma.human.entity.SlaveEntity;

import java.util.Date;

public class AcquisitionResponseDto {

    private long id;
    private double price;
    private Date date;
    private FamilyResponseDto family;
    private SlaveResponseDTO slave;

    public AcquisitionResponseDto(long id, double price, Date date, FamilyResponseDto family, SlaveResponseDTO slave) {
        this.id = id;
        this.price = price;
        this.date = date;
        this.family = family;
        this.slave = slave;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public FamilyResponseDto getFamily() {
        return family;
    }

    public void setFamily(FamilyResponseDto family) {
        this.family = family;
    }

    public SlaveResponseDTO getSlave() {
        return slave;
    }

    public void setSlave(SlaveResponseDTO slave) {
        this.slave = slave;
    }

    public static AcquisitionResponseDto toDto(AcquisitionEntity dto) {
        return new AcquisitionResponseDto(
                dto.getId(),
                dto.getPrice(),
                dto.getDate(),
                FamilyResponseDto.fromFamily(dto.getFamily()),
                SlaveResponseDTO.fromSlave(dto.getSlave())
        );
    }
}
