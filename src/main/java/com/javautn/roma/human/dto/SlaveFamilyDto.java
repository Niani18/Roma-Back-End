package com.javautn.roma.human.dto;

import com.javautn.roma.acquisition.entity.AcquisitionEntity;
import com.javautn.roma.acquisition.entity.State;
import com.javautn.roma.family.dto.FamilyResponseDto;

import java.util.Date;

public class SlaveFamilyDto {

    private long acquisitionId;
    private double price;
    private Date acquisitionDate;
    private State state;
    private FamilyResponseDto family;

    protected SlaveFamilyDto() {}

    public SlaveFamilyDto(long acquisitionId, double price, Date acquisitionDate, State state, FamilyResponseDto family) {
        this.acquisitionId = acquisitionId;
        this.price = price;
        this.acquisitionDate = acquisitionDate;
        this.state = state;
        this.family = family;
    }

    public long getAcquisitionId() {
        return acquisitionId;
    }

    public double getPrice() {
        return price;
    }

    public Date getAcquisitionDate() {
        return acquisitionDate;
    }

    public State getState() {
        return state;
    }

    public FamilyResponseDto getFamily() {
        return family;
    }

    public static SlaveFamilyDto fromAcquisition(AcquisitionEntity acquisition) {
        return new SlaveFamilyDto(
                acquisition.getId(),
                acquisition.getPrice(),
                acquisition.getDate(),
                acquisition.getState(),
                FamilyResponseDto.fromFamily(acquisition.getFamily())
        );
    }
}
