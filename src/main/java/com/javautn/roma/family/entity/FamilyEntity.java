package com.javautn.roma.family.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "family")
public class FamilyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(length = 50, nullable = false)
    String name;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FamilyEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        return ((o instanceof FamilyEntity family) &&
                this.id != family.id &&
                this.name.equals(family.name)
        );
    }

}
