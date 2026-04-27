package com.javautn.roma.human.dto;

import com.javautn.roma.human.entity.SlaveEntity;

import java.util.Date;

public class SlaveResponseDTO extends HumanResponseDTO {
    public final double price;

    public SlaveResponseDTO(long id, String fullName,
                            Date birthDate, Date deathDate,
                            double price) {
        super(id, fullName, birthDate, deathDate);
        this.price = price;
    }

    public static SlaveResponseDTO fromSlave(final SlaveEntity slave) {
        return new SlaveResponseDTO(
                slave.getId(),
                slave.getFullName(),
                slave.getBirthDate(),
                slave.getDeathDate(),
                slave.getPrice()
        );
    }
}
