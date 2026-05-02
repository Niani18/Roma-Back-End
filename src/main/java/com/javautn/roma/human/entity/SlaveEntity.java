package com.javautn.roma.human.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;


@Entity
@Table(name = "slave_entity")
public class SlaveEntity extends HumanEntity {

    @Column protected double price;

    protected SlaveEntity() {
        super();
    }

    public SlaveEntity(
            final String fullName,
            final Date birthDate,
            final double price
    ) {
        super(fullName, birthDate);
        this.price = price;
    }

    @Override
    public String toString() {
        return "Slave={ id="+ super.id +
                " fullName='" + super.fullName + "'" +
                " birthDate='" + super.birthDate + "'" +
                " deathDate='" + super.deathDate + "' }";
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
