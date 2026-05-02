package com.javautn.roma.holding.service;

import com.javautn.roma.family.entity.FamilyEntity;
import com.javautn.roma.family.repository.FamilyRepository;
import com.javautn.roma.holding.dto.HoldingCreateDto;
import com.javautn.roma.holding.entity.HoldingEntity;
import com.javautn.roma.holding.entity.HoldingState;
import com.javautn.roma.holding.repository.HoldingRepository;
import com.javautn.roma.property.entity.PropertyEntity;
import com.javautn.roma.property.repository.PropertyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Optional<FamilyEntity> fam = familyRepository.findById(dto.getFamilyId());
        Optional<PropertyEntity> prop = propertyRepository.findById(dto.getPropertyId());

        if (fam.isPresent() && prop.isPresent()) {
            PropertyEntity property = prop.get();

            holdingRepository.findByPropertyIdAndState(property.getId(), HoldingState.ACTIVE)
                    .forEach(activeHolding -> activeHolding.setState(HoldingState.INACTIVE));

            HoldingEntity ho = new HoldingEntity(property, fam.get(), dto.getPrice(), dto.getDate());
            return holdingRepository.save(ho);
        }else  {
            return null;
        }
    }

}
