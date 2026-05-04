package com.javautn.roma.legalCase.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public class LegalCaseCreateDto {

    @NotBlank
    private Date startDate;
    @NotBlank
    private Date endDate;
    @NotBlank
    private String state;

    public LegalCaseCreateDto(Date startDate, Date endDate, String state) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.state = state;
    }

    public LegalCaseCreateDto() {}

    public Date getStartDate() {return startDate;}
    public void setStartDate(Date startDate) {this.startDate = startDate;}

    public Date getEndDate() {return endDate;}
    public void setEndDate(Date endDate) {this.endDate = endDate;}

    public String getState() {return state;}
    public void setState(String state) {this.state = state;}
}
