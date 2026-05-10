package com.javautn.roma.crime.entity;

import com.javautn.roma.legalCase.entity.LegalCaseEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity()
@Table(name = "crime")
public class CrimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String description;

    @OneToMany(mappedBy = "crime", cascade = CascadeType.ALL)
    private final List<LegalCaseEntity> legalCases = new ArrayList<>();

    public CrimeEntity(String description) {
        this.description = description;
    }

    public long getId() {return id;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public List<LegalCaseEntity> getLegalCases() {
        return legalCases;
    }

    protected CrimeEntity() {}

    @Override
    public String toString() {
        return "Crime Entity{" + "id=" + id + ", description=" + description + '}';}

    @Override
    public boolean equals(Object o) {
        return ((o instanceof CrimeEntity crime) &&
                !Objects.equals(this.id, crime.id) &&
                this.description.equals(crime.description)
        );
    }

}
