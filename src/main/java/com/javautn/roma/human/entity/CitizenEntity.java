package com.javautn.roma.human.entity;

import com.javautn.roma.crime.entity.CrimeEntity;
import com.javautn.roma.familyRol.entity.FamilyRolEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "citizen_entity")
public class CitizenEntity extends HumanEntity {

    @Column( length = 50, nullable = false ) private String socialRole;

    protected CitizenEntity() {
        super();
    }

    public CitizenEntity(
            final String fullName,
            final Date birthDate,
            final String socialRole
    ) {
        super(fullName, birthDate);
        this.socialRole = socialRole;
    }
    @OneToMany(mappedBy = "citizen", cascade = CascadeType.ALL)
    private final List<CrimeEntity> crimes = new ArrayList<>();

    @OneToMany(mappedBy = "citizen",  cascade = CascadeType.ALL)
    private final List<FamilyRolEntity> familyRol = new ArrayList<>();

    public List<CrimeEntity> getCrimes() {
        return crimes;
    }

    public  List<FamilyRolEntity> getFamilyRol() {
        return familyRol;
    }

    @Override
    public String toString() {
        return "Citizen={ id" + super.id +
                " fullName='" + super.fullName + "'" +
                " birthDate='" + super.birthDate + "'" +
                " deathDate='" + super.deathDate + "'" +
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
