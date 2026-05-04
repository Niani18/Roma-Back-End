package com.javautn.roma.familyRol.service;

import com.javautn.roma.family.entity.FamilyEntity;
import com.javautn.roma.family.service.FamilyService;
import com.javautn.roma.familyRol.dto.FamilyRolCreateDto;
import com.javautn.roma.familyRol.entity.FamilyRolEntity;
import com.javautn.roma.familyRol.repository.FamilyRolRepository;
import com.javautn.roma.human.entity.CitizenEntity;
import com.javautn.roma.human.service.HumanService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FamilyRolService {

    private final FamilyRolRepository familyRolRepository;
    private final FamilyService familyService;
    private final HumanService humanService;

    public FamilyRolService(FamilyRolRepository familyRolRepository,  FamilyService familyService, HumanService humanService) {
        this.familyRolRepository = familyRolRepository;
        this.familyService = familyService;
        this.humanService = humanService;
    }

    public Optional<FamilyRolEntity> crateFamilyRol (FamilyRolCreateDto dto) {
        Optional<CitizenEntity> citizen = humanService.getCitizen(dto.getCitizenId());
        Optional<FamilyEntity> family = familyService.getOneFamily(dto.getFamilyId());

        if (citizen.isPresent() && family.isPresent()) {
            FamilyRolEntity familyRol = new FamilyRolEntity(family.get(), citizen.get(), dto.getJoingDate(), dto.getRolName());
            return Optional.of(familyRolRepository.save(familyRol));
        }
        return Optional.empty();
    }

}
