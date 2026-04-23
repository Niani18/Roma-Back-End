package com.javautn.roma.human.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;


@Entity
@DiscriminatorValue("Slave")
public class SlaveEntity extends HumanEntity {

    @Column protected float price;

    protected SlaveEntity() {
        super();
        super.subclass = SlaveEntity.class.getName();
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
}
