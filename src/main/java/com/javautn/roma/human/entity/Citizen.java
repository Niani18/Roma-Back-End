package com.javautn.roma.human.entity;

import jakarta.persistence.Column;

public class Citizen extends Human {

    @Column(length = 50, nullable = true)
    private String socialRole;

    @Override
    public String toString() {
        return "Citizen={ id" + super.id +
                " fullName='" + super.fullName + "'" +
                " birthDate='" + super.birthDate.toString() + "'" +
                " deathDate='" + super.deathDate.toString() + "'" +
                " socialRole=" + this.socialRole + " }";
    }

    @Override
    public boolean equals(final Object B) {
        return (B instanceof Citizen cit &&
                super.equals(B) &&
                this.socialRole.equals(cit.socialRole)
        );
    }

    public String getSocialRole() {
        return socialRole;
    }

    public void setSocialRole(String socialRole) {
        this.socialRole = socialRole;
    }
}
