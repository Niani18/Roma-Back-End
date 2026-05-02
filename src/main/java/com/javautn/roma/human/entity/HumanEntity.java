package com.javautn.roma.human.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@MappedSuperclass
public abstract class HumanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    @Column(length = 50, nullable = false)  protected String fullName;
    @Column( nullable = false )             protected Date birthDate;
    @Column( nullable = true)               protected Date deathDate;

    protected HumanEntity() {}

    public HumanEntity(String fullName, Date birthDate) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.deathDate = null;
    }


    @Override
    public String toString() {
        return "Human={ id=" + this.id +
                " fullName='" + this.fullName + "'" +
                " birthDate='" + this.birthDate + "'" +
                " deathDate='" + this.deathDate + "' }";
    }

    @Override
    public boolean equals(final Object B) {
        return (B instanceof HumanEntity human &&
                this.id != human.id &&
                this.fullName.equals(human.fullName) &&
                this.birthDate.equals(human.birthDate) &&
                Objects.equals(this.deathDate, human.deathDate)
        );
    }


    public Date getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
