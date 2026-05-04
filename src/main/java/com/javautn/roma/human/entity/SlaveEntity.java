package com.javautn.roma.human.entity;

import com.javautn.roma.acquisition.entity.AcquisitionEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "slave_entity")
public class SlaveEntity extends HumanEntity {

    @Column protected double price;

    @OneToMany(mappedBy = "slave")
    private final List<AcquisitionEntity> acquisitions = new ArrayList<>();

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

    public List<AcquisitionEntity> getAcquisitions() {
        return acquisitions;
    }
}
