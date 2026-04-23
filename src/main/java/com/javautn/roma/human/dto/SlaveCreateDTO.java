package com.javautn.roma.human.dto;

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
}
