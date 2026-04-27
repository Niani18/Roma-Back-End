package com.javautn.roma.human.dto;

import com.javautn.roma.human.entity.CitizenEntity;
import com.javautn.roma.human.entity.HumanEntity;
import com.javautn.roma.human.entity.SlaveEntity;

import java.util.Date;

public class HumanResponseDTO {
    public final long id;
    public final String fullName;
    public final Date birthDate;
    public final Date deathDate;

    public HumanResponseDTO(
            final long id,
            final String fullName,
            final Date birthDate,
            final Date deathDate) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.deathDate = deathDate;;
    }
}
