package com.javautn.roma.tax.entity;

public class Tax {
    private int id;
    private String name;
    private String description;

    public Tax() {
        this.id = 0;
        this.name = null;
        this.description = null;
    }

    public Tax(final String name, final String description) {
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
        return (B instanceof Tax tax &&
                this.id == tax.id &&
                this.name.equals(tax.name) &&
                this.description.equals(tax.description)
        );
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
