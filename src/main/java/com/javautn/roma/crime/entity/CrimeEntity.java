package com.javautn.roma.crime.entity;

import com.javautn.roma.human.entity.CitizenEntity;
import jakarta.persistence.*;

import java.util.Objects;

@Entity()
//@Table(description = "crime")
public class CrimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "citizen_id", nullable = false)
    private CitizenEntity citizen;

    public CrimeEntity(String description) {
    }

    public long getId() {return id;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public CitizenEntity getCitizen() {

        return citizen;
    }

    public void setCitizen(CitizenEntity citizen) {
        this.citizen = citizen;
    }

    protected CrimeEntity() {}

    public  CrimeEntity(String description, CitizenEntity citizen) {
        this.description = description;
        this.citizen = citizen;
    }

    @Override
    public String toString() {
        return "Crime Entity{" + "id=" + id + ", description=" + description + ", citizen=" + citizen + '}';}

    @Override
    public boolean equals(Object o) {
        return ((o instanceof CrimeEntity crime) &&
                !Objects.equals(this.id, crime.id) &&
                this.description.equals(crime.description)
        );
    }

}
