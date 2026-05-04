package com.javautn.roma.acquisition.service;

import com.javautn.roma.acquisition.dto.AcquisitionCreateDto;
import com.javautn.roma.acquisition.entity.AcquisitionEntity;
import com.javautn.roma.acquisition.entity.State;
import com.javautn.roma.acquisition.repository.AcquisitionRepository;
import com.javautn.roma.family.entity.FamilyEntity;
import com.javautn.roma.family.service.FamilyService;
import com.javautn.roma.human.entity.SlaveEntity;
import com.javautn.roma.human.service.HumanService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AcquisitionService {

    private final AcquisitionRepository acquisitionRepository;
    private final HumanService humanService;
    private final FamilyService familyService;

    public AcquisitionService(AcquisitionRepository acquisitionRepository, HumanService humanService, FamilyService familyService) {
        this.acquisitionRepository = acquisitionRepository;
        this.humanService = humanService;
        this.familyService = familyService;
    }

    @Transactional
    public Optional<AcquisitionEntity> createAcquisition(AcquisitionCreateDto dto) {

        Optional<SlaveEntity> slave = humanService.getSlave(dto.getSlave());
        Optional<FamilyEntity> family = familyService.getOneFamily(dto.getFamily());

        if (slave.isEmpty() ||  family.isEmpty() || slave.get().getDeathDate() != null) {
            return Optional.empty();
        }

        acquisitionRepository.findByStateAndSlaveId(State.ACTIVE, dto.getSlave())
                .forEach(acquisition -> {
                    acquisition.setState(State.INACTIVE);
                });


        AcquisitionEntity ac = new AcquisitionEntity(dto.getPrice(), dto.getDate(), slave.get(), family.get(), State.ACTIVE);

        return Optional.of(acquisitionRepository.save(ac));

    }

}
