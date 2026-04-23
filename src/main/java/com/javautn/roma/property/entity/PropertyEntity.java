package com.javautn.roma.property.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "property")
public class PropertyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String description;

    protected PropertyEntity() {}

    public PropertyEntity(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PropertyEntity={" + "id=" + id + ", " +
                "name=" + name +
                ", description=" + description + '}';
    }

    @Override
    public boolean equals(final Object o) {
        return ((o instanceof PropertyEntity property) &&
                this.id != property.id &&
                this.name.equals(property.name) &&
                this.description.equals(property.description)
                );
    }

}
