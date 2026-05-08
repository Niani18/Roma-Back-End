package com.javautn.roma.sentence.entity;

import com.javautn.roma.legalCase.entity.LegalCaseEntity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "sentence")

public class SentenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 100, nullable = false)
    private String description;

    @Column(nullable = false)
    private Date applicationDate;

    @Column(nullable = false)
    private Date estimatedEndDate;

    @Column()
    private Date finalEndDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "legal_case_id", nullable = false)
    private LegalCaseEntity legalCase;

    protected SentenceEntity(String description, long idLegalCase, Date applicationDate, Date estimatedEndDate) {}

    public SentenceEntity(String description, Date applicationDate, Date estimatedEndDate, Date finalEndDate) {
        this.description = description;
        this.applicationDate = applicationDate;
        this.estimatedEndDate = estimatedEndDate;
        this.finalEndDate = finalEndDate;
    }

    public SentenceEntity() {

    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getApplicationDate() { return applicationDate; }
    public void setApplicationDate(Date applicationDate) { this.applicationDate = applicationDate; }

    public Date getEstimatedEndDate() { return estimatedEndDate; }
    public void setEstimatedEndDate(Date estimatedEndDate) { this.estimatedEndDate = estimatedEndDate; }

    public Date getFinalEndDate() { return finalEndDate; }
    public void setFinalEndDate(Date finalEndDate) { this.finalEndDate = finalEndDate; }

    public LegalCaseEntity getLegalCase() { return legalCase; }
    public void setLegalCase(LegalCaseEntity legalCase) { this.legalCase = legalCase; }

    @Override
    public String toString() {return "SentenceEntity={id=" + id + ", description=" + description + ", applicationDate=" + applicationDate + ", estimatedEndDate=" + estimatedEndDate + ", finalEndDate=" + finalEndDate + "}";}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return false;
    }
}
