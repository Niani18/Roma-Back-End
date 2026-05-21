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
import com.javautn.roma.shared.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    public AcquisitionEntity createAcquisition(AcquisitionCreateDto dto) {

        SlaveEntity slave = humanService.getSlave(dto.getSlave());
        FamilyEntity family = familyService.getOneFamily(dto.getFamily());

        if (slave.getDeathDate() != null) {
            throw new BadRequestException("Cannot create acquisition for dead slave with id " + dto.getSlave());
        }

        acquisitionRepository.findByStateAndSlaveId(State.ACTIVE, dto.getSlave())
                .forEach(acquisition -> {
                    acquisition.setState(State.INACTIVE);
                });


        Date date = dto.getDate() != null ? dto.getDate() : new Date();
        AcquisitionEntity ac = new AcquisitionEntity(dto.getPrice(), date, slave, family, State.ACTIVE);

        return acquisitionRepository.save(ac);

    }

}
