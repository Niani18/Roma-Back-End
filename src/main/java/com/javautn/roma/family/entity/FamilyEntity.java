package com.javautn.roma.family.entity;

import com.javautn.roma.familyRol.entity.FamilyRolEntity;
import com.javautn.roma.holding.entity.HoldingEntity;
import com.javautn.roma.province.entity.ProvinceEntity;
import com.javautn.roma.tax.entity.TaxAssignationEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "family")
public class FamilyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id", nullable = false)
    private ProvinceEntity province;

    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL)
    private List<TaxAssignationEntity> assignations;

    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL)
    private final List<HoldingEntity> holding =  new ArrayList<>();

    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL)
    private final List<FamilyRolEntity> familyRol = new ArrayList<>();

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProvinceEntity getProvince() {
        return province;
    }

    public void setProvince(ProvinceEntity province) {
        this.province = province;
    }

    protected FamilyEntity() {}

    public FamilyEntity(String name,  ProvinceEntity province) {
        this.name = name;
        this.province = province;
    }

    public List<HoldingEntity> getHoldings() {
        return holding;
    }

    public List<FamilyRolEntity> getFamilyRol() {
        return familyRol;
    }

    @Override
    public String toString() {
        return "FamilyEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        return ((o instanceof FamilyEntity family) &&
                this.id != family.id &&
                this.name.equals(family.name)
        );
    }

}
