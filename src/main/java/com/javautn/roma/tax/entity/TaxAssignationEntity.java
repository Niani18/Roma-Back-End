package com.javautn.roma.tax.entity;


import com.javautn.roma.family.entity.FamilyEntity;
import com.javautn.roma.property.entity.PropertyEntity;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tax_assignation")
public class TaxAssignationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false) private double amount;
    @Column(nullable = false) private Date expiryDate;
    @Column(nullable = true) private Date paymentDate;
    @Column(nullable = false, length = 16) private String state;
    @Column(nullable = false) private double interest;
    @Column(nullable = true) private String sanction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tax_id", nullable = false)
    private TaxEntity tax;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_id")
    private FamilyEntity family;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    private PropertyEntity property;

    protected TaxAssignationEntity() {}

    public TaxAssignationEntity(
            double amount, Date expiryDate, Date paymentDate, String state,
            float interest, String sanction) {
        this.amount = amount;
        this.expiryDate = expiryDate;
        this.paymentDate = paymentDate;
        this.state = state;
        this.interest = interest;
        this.sanction = sanction;
    }

    @Override
    public String toString() {
        return "TaxAssignationEntity{" +
                "amount=" + amount +
                ", expiryDate=" + expiryDate +
                ", paymentDate=" + paymentDate +
                ", state='" + state + '\'' +
                ", interest=" + interest +
                ", sanction='" + sanction + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxAssignationEntity that = (TaxAssignationEntity) o;
        return Double.compare(amount, that.amount) == 0 && Double.compare(interest, that.interest) == 0 && Objects.equals(expiryDate, that.expiryDate) && Objects.equals(paymentDate, that.paymentDate) && Objects.equals(state, that.state) && Objects.equals(sanction, that.sanction);
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public String getSanction() {
        return sanction;
    }

    public void setSanction(String sanction) {
        this.sanction = sanction;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
