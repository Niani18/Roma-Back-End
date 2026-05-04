package com.javautn.roma.acquisition.entity;

import com.javautn.roma.family.entity.FamilyEntity;
import com.javautn.roma.human.entity.SlaveEntity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "acquisition")
public class AcquisitionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "slave_id")
    private SlaveEntity slave;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_id")
    private FamilyEntity family;

    @Enumerated
    @Column
    private State state;

    public AcquisitionEntity(double price, Date date, SlaveEntity slave, FamilyEntity family, State state) {
        this.price = price;
        this.date = date;
        this.slave = slave;
        this.family = family;
        this.state = state;
    }

    protected AcquisitionEntity() {}

    public long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public SlaveEntity getSlave() {
        return slave;
    }

    public void setSlave(SlaveEntity slave) {
        this.slave = slave;
    }

    public FamilyEntity getFamily() {
        return family;
    }

    public void setFamily(FamilyEntity family) {
        this.family = family;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
