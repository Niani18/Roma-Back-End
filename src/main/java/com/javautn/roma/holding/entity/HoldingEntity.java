package com.javautn.roma.holding.entity;

import com.javautn.roma.family.entity.FamilyEntity;
import com.javautn.roma.property.entity.PropertyEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "holding")
public class HoldingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id",  nullable = false)
    private PropertyEntity property;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_id",  nullable = false)
    private FamilyEntity family;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Date date;

    protected HoldingEntity() {}

    public HoldingEntity(PropertyEntity property,  FamilyEntity family, BigDecimal price, Date date) {
        this.property = property;
        this.date = new Date();
        this.family = family;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public PropertyEntity getProperty() {
        return property;
    }

    public void setProperty(PropertyEntity property) {
        this.property = property;
    }

    public FamilyEntity getFamily() {
        return family;
    }

    public void setFamily(FamilyEntity family) {
        this.family = family;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
