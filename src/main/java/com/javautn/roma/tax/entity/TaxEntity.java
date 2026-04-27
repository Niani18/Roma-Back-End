package com.javautn.roma.tax.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tax")
public class TaxEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 64) private String name;
    @Column(nullable = false) private String description;

    public TaxEntity() {
        this.id = 0;
        this.name = null;
        this.description = null;
    }

    public TaxEntity(final String name, final String description) {
        this.id = 0;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Tax={ id=" + this.id +
                " name='" + this.name + "'" +
                " description='" + this.description + "' }";
    }

    @Override
    public boolean equals(final Object B) {
        return (B instanceof TaxEntity tax &&
                this.id == tax.id &&
                this.name.equals(tax.name) &&
                this.description.equals(tax.description)
        );
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
