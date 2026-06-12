package com.javautn.roma.sentence.dto;

import com.javautn.roma.sentence.entity.SentenceEntity;

import java.util.Date;

public class SentenceResponseDto {
    public long id;
    public String description;
    public long idLegalCase;
    public Date applicationDate;
    public Date estimatedEndDate;
    public Date finalEndDate;

    public SentenceResponseDto( long id, String description, long idLegalCase, Date applicationDate, Date estimatedEndDate, Date finalEndDate) {
        this.id = id;
        this.description = description;
        this.idLegalCase = idLegalCase;
        this.applicationDate = applicationDate;
        this.estimatedEndDate = estimatedEndDate;
        this.finalEndDate = finalEndDate;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public long getIdLegalCase() {
        return idLegalCase;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public Date getEstimatedEndDate() {
        return estimatedEndDate;
    }

    public Date getFinalEndDate() {
        return finalEndDate;
    }

    public void setFinalEndDate(Date finalEndDate) {
        this.finalEndDate = finalEndDate;
    }

    public void setEstimatedEndDate(Date estimatedEndDate) {
        this.estimatedEndDate = estimatedEndDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public void setIdLegalCase(long idLegalCase) {
        this.idLegalCase = idLegalCase;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static SentenceResponseDto fromEntity(SentenceEntity se) {
        return new SentenceResponseDto(
            se.getId(), se.getDescription(), se.getLegalCase().getId(), se.getApplicationDate(), se.getEstimatedEndDate(), se.getFinalEndDate()
        );
    }
}
