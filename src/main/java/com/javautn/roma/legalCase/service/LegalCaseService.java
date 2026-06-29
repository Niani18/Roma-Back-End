package com.javautn.roma.legalCase.service;

import com.javautn.roma.crime.entity.CrimeEntity;
import com.javautn.roma.crime.repository.CrimeRepository;
import com.javautn.roma.human.entity.CitizenEntity;
import com.javautn.roma.human.repository.CitizenRepository;
import com.javautn.roma.legalCase.dto.LegalCaseCreateDto;
import com.javautn.roma.legalCase.dto.LegalCaseUpdateDto;
import com.javautn.roma.legalCase.entity.LegalCaseEntity;
import com.javautn.roma.legalCase.repository.LegalCaseRepository;
import com.javautn.roma.shared.exception.ConflictException;
import com.javautn.roma.shared.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LegalCaseService {

    private final  LegalCaseRepository legalCaseRepository;
    private final CitizenRepository citizenRepository;
    private final CrimeRepository crimeRepository;

    public LegalCaseService(
            LegalCaseRepository legalCaseRepository,
            CitizenRepository citizenRepository,
            CrimeRepository crimeRepository) {
        this.legalCaseRepository = legalCaseRepository;
        this.citizenRepository = citizenRepository;
        this.crimeRepository = crimeRepository;
    }

    public List<LegalCaseEntity> getAllLegalCase(){
        return legalCaseRepository.findAll();
    }

    public LegalCaseEntity getOneLegalCase(long id){
        return legalCaseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Legal case not found with id " + id));
    }

    public LegalCaseEntity createLegalCase(LegalCaseCreateDto dto){
        CitizenEntity citizen = citizenRepository.findById(dto.getCitizenId())
                .orElseThrow(() -> new NotFoundException("Citizen not found with id " + dto.getCitizenId()));
        CrimeEntity crime = crimeRepository.findById(dto.getCrimeId())
                .orElseThrow(() -> new NotFoundException("Crime not found with id " + dto.getCrimeId()));

        if (dto.getStartDate().before(citizen.getBirthDate())) {
            throw new ConflictException("The legal case start date cannot be before the citizen birth date");
        }

        LegalCaseEntity legalCase = new LegalCaseEntity(
                dto.getStartDate(),
                null,
                false,
                citizen,
                crime
        );

        return legalCaseRepository.save(legalCase);
    }

    public LegalCaseEntity updateLegalCase(LegalCaseUpdateDto dto, long id){

        LegalCaseEntity legalCase = getOneLegalCase(id);

        if(legalCase.getEndDate() != null){
            throw new ConflictException("Legal case with id " + id + " is already closed");
        }
        if (dto.getEndDate() != null) {
            if (dto.getEndDate().before(legalCase.getStartDate())) {
                throw new ConflictException("The end date cannot be before the start date (" + legalCase.getStartDate() + ")");
            }
        }

        legalCase.setEndDate(dto.getEndDate());
        legalCase.setState(dto.getState());
        return legalCaseRepository.save(legalCase);
    }
}
