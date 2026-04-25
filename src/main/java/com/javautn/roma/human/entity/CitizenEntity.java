package com.javautn.roma.human.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("Citizen")
public class CitizenEntity extends HumanEntity {

    @Column( length = 50, nullable = false ) private String socialRole;

    protected CitizenEntity() {
        super();
    }


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
        return (B instanceof CitizenEntity cit &&
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
