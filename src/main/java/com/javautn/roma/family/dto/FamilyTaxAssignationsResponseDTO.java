package com.javautn.roma.family.dto;

import com.javautn.roma.family.entity.FamilyEntity;
import com.javautn.roma.tax.dto.TaxAssignationResponseDTO;

import java.util.List;

public class FamilyTaxAssignationsResponseDTO {

    private long family_id;
    private String name;

    private List<TaxAssignationResponseDTO> listedTaxes;

    public FamilyTaxAssignationsResponseDTO(
            List<TaxAssignationResponseDTO> listedTaxes, String name, long id) {
        this.listedTaxes = listedTaxes;
        this.name = name;
        this.family_id = id;
    }

    public long getId() {
        return family_id;
    }

    public void setId(long id) {
        this.family_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TaxAssignationResponseDTO> getListedTaxes() {
        return listedTaxes;
    }

    public void setListedTaxes(List<TaxAssignationResponseDTO> listedTaxes) {
        this.listedTaxes = listedTaxes;
    }

    public static FamilyTaxAssignationsResponseDTO fromFamily(FamilyEntity family) {
        return new FamilyTaxAssignationsResponseDTO(
                family.getTaxAssignations().stream().map(TaxAssignationResponseDTO::fromTaxAssignation).toList(),
                family.getName(),
                family.getId()
        );
    }
}
