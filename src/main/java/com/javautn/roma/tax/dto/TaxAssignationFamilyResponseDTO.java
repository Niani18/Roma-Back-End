package com.javautn.roma.tax.dto;

import com.javautn.roma.family.dto.FamilyResponseDto;
import com.javautn.roma.tax.entity.TaxAssignationEntity;

public class TaxAssignationFamilyResponseDTO extends TaxAssignationResponseDTO {

    private FamilyResponseDto family;

    protected TaxAssignationFamilyResponseDTO() {}

    public TaxAssignationFamilyResponseDTO(TaxAssignationResponseDTO base, FamilyResponseDto family) {
        super(base.getId(), base.getAmount(), base.getExpiryDate(), base.getPaymentDate(),
                base.getState(), base.getInterest(), base.getSanction(), base.getTax());
        this.family = family;
    }

    public FamilyResponseDto getFamily() {
        return family;
    }

    public static TaxAssignationFamilyResponseDTO fromTaxAssignation(TaxAssignationEntity assignation) {
        if (assignation == null) return null;
        return new TaxAssignationFamilyResponseDTO(
                TaxAssignationResponseDTO.fromBase(assignation),
                assignation.getFamily() == null ? null : FamilyResponseDto.fromFamily(assignation.getFamily())
        );
    }
}
