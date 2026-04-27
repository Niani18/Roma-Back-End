package com.javautn.roma.province.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity()
@Table(name = "provincia")
public class ProvinceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    protected ProvinceEntity() {
    }

    public ProvinceEntity(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProvinceEntity={id=" + id + ", name=" + name + "}";
    }

    @Override
    public boolean equals(final Object o) {
        return ((o instanceof ProvinceEntity province) &&
                !Objects.equals(this.id, province.id) &&
                this.name.equals(province.name)
        );
    }


}
