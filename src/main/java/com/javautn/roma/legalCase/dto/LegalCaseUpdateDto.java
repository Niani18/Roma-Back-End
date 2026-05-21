package com.javautn.roma.legalCase.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class LegalCaseUpdateDto {

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Argentina/Buenos_Aires")
    private Date endDate;
    @NotNull
    private String state;

    public LegalCaseUpdateDto() {
    }

    public LegalCaseUpdateDto(Date endDate,  String state) {
        this.state = state;
        this.endDate = endDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
