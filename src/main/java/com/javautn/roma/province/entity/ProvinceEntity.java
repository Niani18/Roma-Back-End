package com.javautn.roma.province.entity;

import jakarta.persistence.*;

@Entity()
@Table(name = "provincia")
public class ProvinceEntity {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length =  50, nullable = false)
    private String name;

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

    public ProvinceEntity(long id, String name) {
        this.id = id;
        this.name = name;
    }

    protected ProvinceEntity() {}

    @Override
    public String toString() {
        return "ProvinceEntity={ig=" + id + ", " +
                                "name=" + name + "}";
    }

    @Override
    public boolean equals(final Object o) {
        return ((o instanceof ProvinceEntity province) &&
                this.id != province.id &&
                this.name.equals(province.name)
        );
    }


}
