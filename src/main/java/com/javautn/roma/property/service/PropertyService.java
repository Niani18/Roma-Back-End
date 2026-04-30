package com.javautn.roma.property.service;

import com.javautn.roma.family.entity.FamilyEntity;
import com.javautn.roma.holding.entity.HoldingEntity;
import com.javautn.roma.property.dto.PropertyCreateDto;
import com.javautn.roma.property.dto.PropertyOwnerDto;
import com.javautn.roma.property.dto.PropertyWithOwnersDto;
import com.javautn.roma.property.entity.PropertyEntity;
import com.javautn.roma.property.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<PropertyWithOwnersDto> findPropertyWithOwners(long id) {
        return propertyRepository.findPropertyWithOwners(id)
                .map(this::toPropertyWithOwnersDto);
    }

    public List<PropertyEntity> findAllPropertyEntities() {
        return propertyRepository.findAll();
    }

    public Optional<PropertyEntity> findPropertyEntity(long id) {
        return propertyRepository.findById(id);
    }

    public PropertyEntity createProperty(PropertyCreateDto dto) {
        PropertyEntity newEntity = new PropertyEntity(dto.getName(), dto.getDescription());
        return propertyRepository.save(newEntity);
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

    private PropertyOwnerDto toPropertyOwnerDto(HoldingEntity holding) {
        FamilyEntity family = holding.getFamily();
        return new PropertyOwnerDto(
                holding.getId(),
                family.getId(),
                family.getName()
        );
    }

}
