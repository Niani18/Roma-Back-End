package com.javautn.roma.human.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

import java.util.Date;


@Entity
@DiscriminatorValue("Slave")
public class SlaveEntity extends HumanEntity {

    @Column protected double price;

    protected SlaveEntity() {
        super();
    }

    public SlaveEntity(
            final String fullName,
            final Date birthDate,
            final Date deathDate,
            final double price
    ) {
        super(fullName, birthDate, deathDate);
        this.price = price;
    }

    @Override
    public String toString() {
        return "Slave={ id="+ super.id +
                " fullName='" + super.fullName + "'" +
                " birthDate='" + super.birthDate.toString() + "'" +
                " deathDate='" + super.deathDate.toString() + "' }";
    }

    @Override
    public boolean equals(Object B) {
        return (B instanceof SlaveEntity slave &&
                slave.price == this.price &&
                super.equals(B)
        );
    }

    public double getPrice() {
        return price;
    }
}
