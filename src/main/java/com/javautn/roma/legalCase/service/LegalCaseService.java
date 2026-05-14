package com.javautn.roma.legalCase.service;

import com.javautn.roma.crime.entity.CrimeEntity;
import com.javautn.roma.crime.repository.CrimeRepository;
import com.javautn.roma.human.entity.CitizenEntity;
import com.javautn.roma.human.repository.CitizenRepository;
import com.javautn.roma.legalCase.dto.LegalCaseCreateDto;
import com.javautn.roma.legalCase.dto.LegalCaseUpdateDto;
import com.javautn.roma.legalCase.entity.LegalCaseEntity;
import com.javautn.roma.legalCase.repository.LegalCaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<LegalCaseEntity> getOneLegalCase(long id){
        return legalCaseRepository.findById(id);
    }

    public Optional<LegalCaseEntity> createLegalCase(LegalCaseCreateDto dto){
        Optional<CitizenEntity> citizen = citizenRepository.findById(dto.getCitizenId());
        Optional<CrimeEntity> crime = crimeRepository.findById(dto.getCrimeId());
        if (citizen.isEmpty() || crime.isEmpty()) {
            return Optional.empty();
        }

        LegalCaseEntity legalCase = new LegalCaseEntity(
                dto.getStartDate(),
                null,
                dto.getState(),
                citizen.get(),
                crime.get()
        );

        return Optional.of(legalCaseRepository.save(legalCase));
    }

    public Optional<LegalCaseEntity> updateLegalCase(LegalCaseUpdateDto dto, long id){

        Optional<LegalCaseEntity> lce = legalCaseRepository.findById(id);

        if(lce.isEmpty() || lce.get().getState() != null){
            return Optional.empty();
        }

        return lce.map(legalCase -> {
            legalCase.setEndDate(dto.getEndDate());
            legalCase.setState(dto.getState());
            return legalCaseRepository.save(legalCase);
        });
    }
}
