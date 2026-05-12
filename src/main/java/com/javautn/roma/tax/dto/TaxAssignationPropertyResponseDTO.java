package com.javautn.roma.tax.dto;

import com.javautn.roma.property.dto.PropertyResponseDto;
import com.javautn.roma.tax.entity.TaxAssignationEntity;

public class TaxAssignationPropertyResponseDTO extends TaxAssignationResponseDTO {

    private PropertyResponseDto property;

    protected TaxAssignationPropertyResponseDTO() {
    }

    public TaxAssignationPropertyResponseDTO(TaxAssignationResponseDTO base, PropertyResponseDto property) {
        super(base.getId(), base.getAmount(), base.getExpiryDate(), base.getPaymentDate(), base.getState(),
                base.getInterest(), base.getSanction(), base.getTax());
        this.property = property;
    }

    public PropertyResponseDto getProperty() {
        return property;
    }

    public static TaxAssignationPropertyResponseDTO fromTaxAssignation(TaxAssignationEntity assignation) {
        if (assignation == null) {
            return null;
        }

        return new TaxAssignationPropertyResponseDTO(
                TaxAssignationResponseDTO.fromBase(assignation),
                assignation.getProperty() == null ? null : PropertyResponseDto.fromProperty(assignation.getProperty())
        );
    }
}
