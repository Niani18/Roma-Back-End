package com.javautn.roma.crime.entity;

import com.javautn.roma.province.entity.ProvinceEntity;
import jakarta.persistence.*;

import java.util.Objects;

@Entity()
//@Table(description = "delito")
public class CrimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String description;

    protected CrimeEntity(){
    }

    public CrimeEntity(String description) {this.description = description;}

    public Long getId() {return id;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    @Override
    public String toString() { return "ProvinceEntity={" + id + ", =" + "description" + description + "}"; }

    @Override
    public boolean equals(Object o) {
        return ((o instanceof CrimeEntity crime) &&
                !Objects.equals(this.id, crime.id) &&
                this.description.equals(crime.description)
        );
    }

}
