package com.javautn.roma.sentence.dto;

import java.util.Date;

public class SentenceResponseDto {
    public final long id;
    public final String description;
    public final long idLegalCause;
    public final Date applicationDate;
    public final Date estimatedEndDate;
    public final Date finalEndDate;

    public SentenceResponseDto(
            final long id,
            final String description,
            final long idLegalCause,
            final Date applicationDate,
            final Date estimatedEndDate,
            final Date finalEndDate) {
        this.id = id;
        this.description = description;
        this.idLegalCause = idLegalCause;
        this.applicationDate = applicationDate;
        this.estimatedEndDate = estimatedEndDate;
        this.finalEndDate = finalEndDate;
    }
}
