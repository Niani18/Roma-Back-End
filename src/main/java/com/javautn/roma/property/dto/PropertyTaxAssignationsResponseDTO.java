package com.javautn.roma.property.dto;

import com.javautn.roma.property.entity.PropertyEntity;
import com.javautn.roma.tax.dto.TaxAssignationResponseDTO;

import java.util.List;

public class PropertyTaxAssignationsResponseDTO {

    private long property_id;
    private String name;
    private String description;

    private List<TaxAssignationResponseDTO> listedTaxes;

    public PropertyTaxAssignationsResponseDTO(
            List<TaxAssignationResponseDTO> listedTaxes, String name, String description, long id) {
        this.listedTaxes = listedTaxes;
        this.name = name;
        this.description = description;
        this.property_id = id;
    }

    public long getId() {
        return property_id;
    }

    public void setId(long id) {
        this.property_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TaxAssignationResponseDTO> getListedTaxes() {
        return listedTaxes;
    }

    public void setListedTaxes(List<TaxAssignationResponseDTO> listedTaxes) {
        this.listedTaxes = listedTaxes;
    }

    public static PropertyTaxAssignationsResponseDTO fromProperty(PropertyEntity property) {
        return new PropertyTaxAssignationsResponseDTO(
                property.getTaxAssignations().stream().map(TaxAssignationResponseDTO::fromTaxAssignation).toList(),
                property.getName(),
                property.getDescription(),
                property.getId()
        );
    }
}
