package com.javautn.roma.familyRol.service;

import com.javautn.roma.family.entity.FamilyEntity;
import com.javautn.roma.family.service.FamilyService;
import com.javautn.roma.familyRol.dto.FamilyRolCreateDto;
import com.javautn.roma.familyRol.entity.FamilyRolEntity;
import com.javautn.roma.familyRol.repository.FamilyRolRepository;
import com.javautn.roma.human.entity.CitizenEntity;
import com.javautn.roma.human.service.HumanService;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    public FamilyRolEntity crateFamilyRol (FamilyRolCreateDto dto) {
        CitizenEntity citizen = humanService.getCitizen(dto.getCitizenId());
        FamilyEntity family = familyService.getOneFamily(dto.getFamilyId());

        Date joiningDate = dto.getJoingDate() == null ? new Date() : dto.getJoingDate();
        FamilyRolEntity familyRol = new FamilyRolEntity(family, citizen, joiningDate, dto.getRolName());
        return familyRolRepository.save(familyRol);
    }

}
