package com.javautn.roma.legalCase.dto;

import java.util.Date;

public class LegalCaseResponseDto {

    private long id;
    private Date startDate;
    private Date endDate;
    private String state;

    public LegalCaseResponseDto() {}

    public LegalCaseResponseDto(long id, Date startDate, Date endDate, String state) {
        this.id = id;
        this.startDate  = startDate;
        this.endDate = endDate;
        this.state = state;
    }

    public long getId() {return id;}

    public Date getStartDate() {return startDate;}

    public Date getEndDate() {return endDate;}

    public String getState() {return state;}
}
