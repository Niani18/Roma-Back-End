package com.javautn.roma.legalCase.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class LegalCaseCreateDto {

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Argentina/Buenos_Aires")
    private Date startDate;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Argentina/Buenos_Aires")
    private Date endDate;
    @NotBlank
    private String state;
    @NotNull
    private Long citizenId;
    @NotNull
    private Long crimeId;

    public LegalCaseCreateDto(Date startDate, Date endDate, String state, Long citizenId, Long crimeId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.state = state;
        this.citizenId = citizenId;
        this.crimeId = crimeId;
    }

    public LegalCaseCreateDto() {}

    public Date getStartDate() {return startDate;}
    public void setStartDate(Date startDate) {this.startDate = startDate;}

    public Date getEndDate() {return endDate;}
    public void setEndDate(Date endDate) {this.endDate = endDate;}

    public String getState() {return state;}
    public void setState(String state) {this.state = state;}

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
