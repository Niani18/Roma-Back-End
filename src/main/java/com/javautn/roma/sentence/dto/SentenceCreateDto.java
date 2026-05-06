package com.javautn.roma.sentence.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class SentenceCreateDto {
    @NotBlank
    private String description;
    private long idLegalCause;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Argentina/Buenos_Aires")
    private Date applicationDate;
    private Date estimatedEndDate;

    protected SentenceCreateDto() {}

    public SentenceCreateDto(String description, long idLegalCause, Date applicationDate, Date estimatedEndDate) {
        this.description = description;
        this.idLegalCause = idLegalCause;
        this.applicationDate = applicationDate;
        this.estimatedEndDate = estimatedEndDate;
    }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public long getIdLegalCause() { return idLegalCause; }
    public void setIdLegalCause(long idLegalCause) {  this.idLegalCause = idLegalCause; }

    public Date getApplicationDate() { return applicationDate; }
    public void setApplicationDate(Date applicationDate) { this.applicationDate = applicationDate; }

    public Date getEstimatedEndDate() { return estimatedEndDate; }

    public void setEstimatedEndDate(Date estimatedEndDate) { this.estimatedEndDate = estimatedEndDate; }
}
