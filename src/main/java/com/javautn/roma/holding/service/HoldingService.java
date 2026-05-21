package com.javautn.roma.holding.service;

import com.javautn.roma.family.entity.FamilyEntity;
import com.javautn.roma.family.repository.FamilyRepository;
import com.javautn.roma.holding.dto.HoldingCreateDto;
import com.javautn.roma.holding.entity.HoldingEntity;
import com.javautn.roma.holding.entity.HoldingState;
import com.javautn.roma.holding.repository.HoldingRepository;
import com.javautn.roma.property.entity.PropertyEntity;
import com.javautn.roma.property.repository.PropertyRepository;
import com.javautn.roma.shared.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class HoldingService {

    private final HoldingRepository holdingRepository;
    private final FamilyRepository familyRepository;
    private final PropertyRepository propertyRepository;

    public HoldingService(HoldingRepository holdingRepository,  FamilyRepository familyRepository, PropertyRepository propertyRepository) {
        this.holdingRepository = holdingRepository;
        this.familyRepository =  familyRepository;
        this.propertyRepository = propertyRepository;
    }

    @Transactional
    public HoldingEntity saveHolding (HoldingCreateDto dto) {
        FamilyEntity family = familyRepository.findById(dto.getFamilyId())
                .orElseThrow(() -> new NotFoundException("Family not found with id " + dto.getFamilyId()));
        PropertyEntity property = propertyRepository.findById(dto.getPropertyId())
                .orElseThrow(() -> new NotFoundException("Property not found with id " + dto.getPropertyId()));

        holdingRepository.findByPropertyIdAndState(property.getId(), HoldingState.ACTIVE)
                .forEach(activeHolding -> activeHolding.setState(HoldingState.INACTIVE));

        Date date = dto.getDate() == null ? new Date() : dto.getDate();
        HoldingEntity ho = new HoldingEntity(property, family, dto.getPrice(), date);
        return holdingRepository.save(ho);
    }

}
