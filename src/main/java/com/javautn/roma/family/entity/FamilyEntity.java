package com.javautn.roma.family.entity;

import com.javautn.roma.province.dto.ProvinceResponseDto;
import com.javautn.roma.province.entity.ProvinceEntity;
import jakarta.persistence.*;

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

    public FamilyEntity() {}

    public FamilyEntity(String name,  ProvinceEntity province) {
        this.name = name;
        this.province = province;
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
