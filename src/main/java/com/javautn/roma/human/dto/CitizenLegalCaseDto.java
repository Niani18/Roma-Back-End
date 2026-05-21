package com.javautn.roma.human.dto;

import com.javautn.roma.legalCase.entity.LegalCaseEntity;

import java.util.Date;

public class CitizenLegalCaseDto {

    private long id;
    private Date startDate;
    private Date endDate;
    private String state;
    private long crimeId;
    private String crimeDescription;

    public CitizenLegalCaseDto(long id, Date startDate, Date endDate, String state, long crimeId, String crimeDescription) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.state = state;
        this.crimeId = crimeId;
        this.crimeDescription = crimeDescription;
    }

    public long getId() {
        return id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getState() {
        return state;
    }

    public long getCrimeId() {
        return crimeId;
    }

    public String getCrimeDescription() {
        return crimeDescription;
    }

    public static CitizenLegalCaseDto fromLegalCase(LegalCaseEntity legalCase) {
        return new CitizenLegalCaseDto(
                legalCase.getId(),
                legalCase.getStartDate(),
                legalCase.getEndDate(),
                legalCase.getState(),
                legalCase.getCrime().getId(),
                legalCase.getCrime().getDescription()
        );
    }
}
