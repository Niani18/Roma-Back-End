package com.javautn.roma.crime.dto;

import com.javautn.roma.crime.entity.CrimeEntity;

public class CrimeResponseDto {

    private long id;
    private String description;

    public CrimeResponseDto() {}
    public CrimeResponseDto(long id, String description) {
        this.id = id;
        this.description = description;
    }

    public long getId() {return id;}

    public String getDescription() {return description;}

    public static CrimeResponseDto fromCrime(CrimeEntity crime) {
        return new CrimeResponseDto(crime.getId(), crime.getDescription());
    }
}
