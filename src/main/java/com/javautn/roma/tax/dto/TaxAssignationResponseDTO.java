package com.javautn.roma.tax.dto;

import com.javautn.roma.tax.entity.StateAsignation;
import com.javautn.roma.tax.entity.TaxAssignationEntity;

import java.util.Date;

public class TaxAssignationResponseDTO {

    private long id;
    private double amount;
    private Date expiryDate;
    private Date paymentDate;
    private StateAsignation state;
    private double interest;
    private String sanction;
    private TaxResponseDTO tax;

    protected TaxAssignationResponseDTO() {}

    public TaxAssignationResponseDTO(long id, double amount, Date expiryDate, Date paymentDate,
                                     StateAsignation state, double interest, String sanction,
                                     TaxResponseDTO tax) {
        this.id = id;
        this.amount = amount;
        this.expiryDate = expiryDate;
        this.paymentDate = paymentDate;
        this.state = state;
        this.interest = interest;
        this.sanction = sanction;
        this.tax = tax;
    }

    public long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public StateAsignation getState() {
        return state;
    }

    public double getInterest() {
        return interest;
    }

    public String getSanction() {
        return sanction;
    }

    public TaxResponseDTO getTax() {
        return tax;
    }

    protected static TaxAssignationResponseDTO fromBase(TaxAssignationEntity assignation) {
        if (assignation == null) return null;
        return new TaxAssignationResponseDTO(
                assignation.getId(),
                assignation.getAmount(),
                assignation.getExpiryDate(),
                assignation.getPaymentDate(),
                assignation.getState(),
                assignation.getInterest(),
                assignation.getSanction(),
                TaxResponseDTO.fromTax(assignation.getTax())
        );
    }

    public static TaxAssignationResponseDTO fromTaxAssignation(TaxAssignationEntity assignation) {
        if (assignation == null) return null;
        if (assignation.getFamily() != null) return TaxAssignationFamilyResponseDTO.fromTaxAssignation(assignation);
        if (assignation.getProperty() != null) return TaxAssignationPropertyResponseDTO.fromTaxAssignation(assignation);
        return fromBase(assignation);
    }
}
