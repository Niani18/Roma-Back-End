package com.javautn.roma.legalCase.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class LegalCaseCreateDto {

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Argentina/Buenos_Aires")
    private Date startDate;
    @NotNull
    private Long citizenId;
    @NotNull
    private Long crimeId;

    public LegalCaseCreateDto(Date startDate, Long citizenId, Long crimeId) {
        this.startDate = startDate;
        this.citizenId = citizenId;
        this.crimeId = crimeId;
    }

    public LegalCaseCreateDto() {}

    public Date getStartDate() {return startDate;}
    public void setStartDate(Date startDate) {this.startDate = startDate;}

    public Long getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(Long citizenId) {
        this.citizenId = citizenId;
    }

    public Long getCrimeId() {
        return crimeId;
    }

    public void setCrimeId(Long crimeId) {
        this.crimeId = crimeId;
    }
}
