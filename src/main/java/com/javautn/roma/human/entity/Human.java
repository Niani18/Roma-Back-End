package com.javautn.roma.human.entity;

import java.util.Date;

public abstract class Human {
    protected long id;
    protected String fullName;
    protected Date birthDate;
    protected Date deathDate;

    public Human() {
        this.id = 0;
        this.fullName = "";
        this.birthDate = null;
        this.deathDate = null;
    }

    @Override
    public String toString() {
        return "Human={ id=" + this.id +
                " fullName='" + this.fullName + "'" +
                " birthDate='" + this.deathDate.toString() + "'" +
                " deathDate='" + this.deathDate.toString() + "' }";
    }

    @Override
    public boolean equals(final Object B) {
        return (B instanceof Human human &&
                this.id != human.id &&
                this.fullName.equals(human.fullName) &&
                this.birthDate.equals(human.birthDate) &&
                this.deathDate.equals(human.deathDate)
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
