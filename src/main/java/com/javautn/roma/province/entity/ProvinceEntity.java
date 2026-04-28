package com.javautn.roma.province.entity;

import com.javautn.roma.family.entity.FamilyEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity()
@Table(name = "province")
public class ProvinceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL)
    private final List<FamilyEntity> families = new ArrayList<>();

    protected ProvinceEntity() {
    }

    public ProvinceEntity(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FamilyEntity> getFamilies() {
        return families;
    }

    @Override
    public String toString() {
        return "ProvinceEntity={id=" + id + ", name=" + name + "}";
    }

    @Override
    public boolean equals(final Object o) {
        return ((o instanceof ProvinceEntity province) &&
                !Objects.equals(this.id, province.id) &&
                this.name.equals(province.name)
        );
    }


}
