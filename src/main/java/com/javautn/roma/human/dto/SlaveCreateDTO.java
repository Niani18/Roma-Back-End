package com.javautn.roma.human.dto;

import com.javautn.roma.human.entity.SlaveEntity;

import java.util.Date;

public class SlaveCreateDTO extends HumanCreateDTO {

    private final float price;

    public SlaveCreateDTO(
            final String fullName,
            final Date birthDate,
            final Date deathDate,
            final float price) {
        super(fullName, birthDate, deathDate);
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public SlaveEntity newSlave() {
        return new SlaveEntity(
                super.getFullName(),
                super.getBirthDate(),
                super.getDeathDate(),
                this.price
        );
    }
}
