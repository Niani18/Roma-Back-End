package com.javautn.roma.human.dto;

import com.javautn.roma.human.entity.SlaveEntity;

public class SlaveCreateDTO extends HumanCreateDTO {

    private float price;

    protected SlaveCreateDTO() {}

    public SlaveCreateDTO(
            final String fullName,
            final java.util.Date birthDate,
            final float price) {
        super(fullName, birthDate);
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public SlaveEntity newSlave() {
        return new SlaveEntity(
                super.getFullName(),
                super.getBirthDate(),
                this.price
        );
    }
}
