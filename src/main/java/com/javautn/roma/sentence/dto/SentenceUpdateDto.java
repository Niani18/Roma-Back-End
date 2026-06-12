package com.javautn.roma.sentence.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Pattern;

import java.util.Date;

public class SentenceUpdateDto {

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Argentina/Buenos_Aires")
    private Date finalEndDate;

    public SentenceUpdateDto() {}

    public Date getFinalEndDate() {
        return finalEndDate;
    }

    public void setFinalEndDate(Date finalEndDate) {
        this.finalEndDate = finalEndDate;
    }
}
