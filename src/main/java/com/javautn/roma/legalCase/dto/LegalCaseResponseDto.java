package com.javautn.roma.legalCase.dto;

import com.javautn.roma.crime.dto.CrimeResponseDto;
import com.javautn.roma.crime.entity.CrimeEntity;
import com.javautn.roma.human.dto.CitizenResponseDTO;
import com.javautn.roma.legalCase.entity.LegalCaseEntity;

import java.util.Date;

public class LegalCaseResponseDto {

    private long id;
    private Date startDate;
    private Date endDate;
    private boolean state;
    private CrimeResponseDto crime;
    private CitizenResponseDTO citizen;

    public LegalCaseResponseDto() {}

    public LegalCaseResponseDto(long id, Date startDate, Date endDate, boolean state, CrimeResponseDto crime, CitizenResponseDTO citizen) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.state = state;
        this.crime = crime;
        this.citizen = citizen;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public CrimeResponseDto getCrime() {
        return crime;
    }

    public void setCrime(CrimeResponseDto crime) {
        this.crime = crime;
    }

    public CitizenResponseDTO getCitizen() {
        return citizen;
    }

    public void setCitizen(CitizenResponseDTO citizen) {
        this.citizen = citizen;
    }

    public static LegalCaseResponseDto fromLegalCase(LegalCaseEntity legalCase) {
        return new LegalCaseResponseDto(
                legalCase.getId(),
                legalCase.getStartDate(),
                legalCase.getEndDate(),
                legalCase.getState(),
                CrimeResponseDto.fromCrime(legalCase.getCrime()),
                CitizenResponseDTO.fromCitizen(legalCase.getCitizen())
        );
    }
}
