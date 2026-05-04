
package com.javautn.roma.legalCase.entity;

import com.javautn.roma.human.entity.CitizenEntity;
import com.javautn.roma.crime.entity.CrimeEntity;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity()
@Table(name = "legalCase")

public class LegalCaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date startDate;
    @Column (nullable = false)
    private Date endDate;
    @Column(length =100, nullable = false)
    private String state;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "citizen_id")
    private CitizenEntity citizen;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "crime_id")
    private CrimeEntity crime;

    protected LegalCaseEntity() {}

    public LegalCaseEntity(Date startDate, Date endDate, String state) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.state = state;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Date getStartDate() { return startDate; }

    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }

    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public String getState() { return state; }

    public void setState(String state) { this.state = state; }

    public CitizenEntity getCitizen() { return citizen; }

    public void setCitizen(CitizenEntity citizen) { this.citizen = citizen; }

    public CrimeEntity getCrime() {return  crime;}

    public void setCrime(CrimeEntity crime) { this.crime = crime; }

    @Override
    public String toString() { return "LegalCaseEntity={id=" + id + ", startDate=" + startDate + ", endDate=" + endDate;}

    @Override
    public boolean equals(Object o) {
        return ((o instanceof LegalCaseEntity legalCase) &&
                !Objects.equals(this.id, legalCase.id)
        );

    }

}
