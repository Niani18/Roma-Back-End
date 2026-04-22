package com.javautn.roma.human.entity;

public class Slave extends Human {


    @Override
    public String toString() {
        return "Slave={ id="+ super.id +
                " fullName='" + super.fullName + "'" +
                " birthDate='" + super.birthDate.toString() + "'" +
                " deathDate='" + super.deathDate.toString() + "' }";
    }

    @Override
    public boolean equals(Object B) {
        return super.equals(B);
    }
}
