package com.javautn.roma.property.entity;

import com.javautn.roma.holding.entity.HoldingEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private final List<HoldingEntity> holdings = new ArrayList<>();

    protected PropertyEntity() {}

    public PropertyEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
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

    public List<HoldingEntity> getHoldings() {
        return holdings;
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
