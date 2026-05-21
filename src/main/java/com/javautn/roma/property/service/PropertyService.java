package com.javautn.roma.property.service;

import com.javautn.roma.family.entity.FamilyEntity;
import com.javautn.roma.holding.entity.HoldingEntity;
import com.javautn.roma.property.dto.PropertyCreateDto;
import com.javautn.roma.property.dto.PropertyOwnerDto;
import com.javautn.roma.property.dto.PropertyWithAllHoldingsDto;
import com.javautn.roma.property.dto.PropertyWithOwnersDto;
import com.javautn.roma.property.entity.PropertyEntity;
import com.javautn.roma.property.repository.PropertyRepository;
import com.javautn.roma.shared.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public List<PropertyWithOwnersDto> findAllPropertyWithOwners() {
        return propertyRepository.findAllPropertyWithOwners().stream()
                .map(this::toPropertyWithOwnersDto)
                .toList();
    }

    public PropertyWithOwnersDto findPropertyWithOwners(long id) {
        return propertyRepository.findPropertyWithOwners(id)
                .map(this::toPropertyWithOwnersDto)
                .orElseThrow(() -> new NotFoundException("Property not found with id " + id));
    }

    public List<PropertyEntity> findAllPropertyEntities() {
        return propertyRepository.findAll();
    }

    public PropertyEntity findPropertyEntity(long id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Property not found with id " + id));
    }

    public PropertyEntity createProperty(PropertyCreateDto dto) {
        PropertyEntity newEntity = new PropertyEntity(dto.getName(), dto.getDescription());
        return propertyRepository.save(newEntity);
    }

    public PropertyWithAllHoldingsDto findOnePropertyWithAllHoldings (long id) {
        return propertyRepository.findAllHoldingsWithOwners(id)
                .map(this::toPropertyWithAllHoldingsDto)
                .orElseThrow(() -> new NotFoundException("Property not found with id " + id));
    }

    public PropertyEntity findPropertyWithTaxAssignations(long id) {
        return propertyRepository.findPropertyWithTaxAssignations(id)
                .orElseThrow(() -> new NotFoundException("Property not found with id " + id));
    }

    private PropertyWithOwnersDto toPropertyWithOwnersDto(PropertyEntity property) {
        PropertyOwnerDto owner = property.getHoldings().stream()
                .map(this::toPropertyOwnerDto)
                .findFirst()
                .orElse(null);

        return new PropertyWithOwnersDto(
                property.getId(),
                property.getName(),
                property.getDescription(),
                owner
        );
    }

    private PropertyWithAllHoldingsDto toPropertyWithAllHoldingsDto(PropertyEntity property) {
        List<PropertyOwnerDto> owner = property.getHoldings().stream()
                .map(this::toPropertyOwnerDto)
                .toList();

        return new PropertyWithAllHoldingsDto(
                property.getId(),
                property.getName(),
                property.getDescription(),
                owner
        );
    }

    private PropertyOwnerDto toPropertyOwnerDto(HoldingEntity holding) {
        FamilyEntity family = holding.getFamily();
        return new PropertyOwnerDto(
                holding.getId(),
                family.getId(),
                family.getName(),
                holding.getPrice(),
                holding.getDate()
        );
    }

}
