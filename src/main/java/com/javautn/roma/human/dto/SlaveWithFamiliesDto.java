package com.javautn.roma.human.dto;

import com.javautn.roma.human.entity.SlaveEntity;

import java.util.Date;
import java.util.List;

public class SlaveWithFamiliesDto {

    private long id;
    private String fullName;
    private Date birthDate;
    private Date deathDate;
    private double price;
    private List<SlaveFamilyDto> families;

    protected SlaveWithFamiliesDto() {}

    public SlaveWithFamiliesDto(long id, String fullName, Date birthDate, Date deathDate,
                                double price, List<SlaveFamilyDto> families) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.price = price;
        this.families = families;
    }

    public long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Date getDeathDate() {
        return deathDate;
    }

    public double getPrice() {
        return price;
    }

    public List<SlaveFamilyDto> getFamilies() {
        return families;
    }

    public static SlaveWithFamiliesDto fromSlaveWith(SlaveEntity slave) {
        List<SlaveFamilyDto> families = slave.getAcquisitions().stream()
                .map(SlaveFamilyDto::fromAcquisition)
                .toList();

        return new SlaveWithFamiliesDto(
                slave.getId(),
                slave.getFullName(),
                slave.getBirthDate(),
                slave.getDeathDate(),
                slave.getPrice(),
                families
        );
    }
}
