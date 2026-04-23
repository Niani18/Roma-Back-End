package com.javautn.roma.provincia.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity()
@Table(name = "provincia")
public class ProvinceEntity {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    public long getIg() {
        return id;
    }

    public void setIg(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProvinceEntity(long id, String name) {
        setIg(id);
        setName(name);
    }

    protected ProvinceEntity() {}

    @Override
    public String toString() {
        return "ProvinceEntity {ig=" + id + ", " +
                                "name=" + name + "}";
    }

}
