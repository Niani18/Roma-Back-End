package com.javautn.roma.familyRol.entity;

import com.javautn.roma.family.entity.FamilyEntity;
import com.javautn.roma.human.entity.CitizenEntity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "familyRol")
public class FamilyRolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_id",  nullable = false)
    private FamilyEntity family;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "citizen_id")
    private CitizenEntity citizen;

    @Column(nullable = false)
    private Date dateOfJoining;

    @Column(nullable = true)
    private Date dateOfUnjoining;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    protected FamilyRolEntity() {}

    public FamilyRolEntity (FamilyEntity family, CitizenEntity citizen, Date dateOfJoining, Role role) {
        this.family = family;
        this.citizen = citizen;
        this.dateOfJoining = dateOfJoining;
        this.dateOfUnjoining = null;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public FamilyEntity getFamily() {
        return family;
    }

    public void setFamily(FamilyEntity family) {
        this.family = family;
    }

    public CitizenEntity getCitizen() {
        return citizen;
    }

    public void setCitizen(CitizenEntity citizen) {
        this.citizen = citizen;
    }

    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public Date getDateOfUnjoining() {
        return dateOfUnjoining;
    }

    public void setDateOfUnjoining(Date dateOfUnjoining) {
        this.dateOfUnjoining = dateOfUnjoining;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
