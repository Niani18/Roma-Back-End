package com.javautn.roma.sentence.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class SentenceCreateDto {
    @NotBlank
    private String description;
    @NotNull
    private long idLegalCase;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Argentina/Buenos_Aires")
    private Date applicationDate;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Argentina/Buenos_Aires")
    private Date estimatedEndDate;

    protected SentenceCreateDto() {}

    public SentenceCreateDto(String description, long idLegalCase, Date applicationDate, Date estimatedEndDate) {
        this.description = description;
        this.idLegalCase = idLegalCase;
        this.applicationDate = applicationDate;
        this.estimatedEndDate = estimatedEndDate;
    }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public long getIdLegalCase() { return idLegalCase; }
    public void setIdLegalCase(long idLegalCase) {
        this.idLegalCase = idLegalCase;
    }

    public Date getApplicationDate() { return applicationDate; }
    public void setApplicationDate(Date applicationDate) { this.applicationDate = applicationDate; }

    public Date getEstimatedEndDate() { return estimatedEndDate; }

    public void setEstimatedEndDate(Date estimatedEndDate) { this.estimatedEndDate = estimatedEndDate; }
}
