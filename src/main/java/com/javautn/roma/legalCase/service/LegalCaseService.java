package com.javautn.roma.legalCase.service;

import com.javautn.roma.crime.entity.CrimeEntity;
import com.javautn.roma.crime.repository.CrimeRepository;
import com.javautn.roma.human.entity.CitizenEntity;
import com.javautn.roma.human.repository.CitizenRepository;
import com.javautn.roma.legalCase.dto.LegalCaseCreateDto;
import com.javautn.roma.legalCase.dto.LegalCaseResponseDto;
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

    public List<LegalCaseResponseDto> getAllLegalCase(){
        return legalCaseRepository.findAll().stream()
                .map(legalCase -> new LegalCaseResponseDto(legalCase.getId(), legalCase.getStartDate(), legalCase.getEndDate(), legalCase.getState()))
                .toList();
    }

    public Optional<LegalCaseResponseDto> getOneLegalCase(long id){
        return legalCaseRepository.findById(id)
                .map(legalCaseEntity -> new LegalCaseResponseDto(legalCaseEntity.getId(), legalCaseEntity.getStartDate(), legalCaseEntity.getEndDate(), legalCaseEntity.getState()));
    }

    public LegalCaseResponseDto createLegalCase(LegalCaseCreateDto dto){
        CitizenEntity citizen = citizenRepository.findById(dto.getCitizenId())
                .orElseThrow(() -> new IllegalArgumentException("Citizen not found"));
        CrimeEntity crime = crimeRepository.findById(dto.getCrimeId())
                .orElseThrow(() -> new IllegalArgumentException("Crime not found"));

        LegalCaseEntity legalCase = new LegalCaseEntity(
                dto.getStartDate(),
                dto.getEndDate(),
                dto.getState(),
                citizen,
                crime
        );
        LegalCaseEntity savedLegalCase = legalCaseRepository.save(legalCase);

        return new LegalCaseResponseDto(savedLegalCase.getId(), savedLegalCase.getStartDate(), savedLegalCase.getEndDate(), savedLegalCase.getState());
    }

    public Optional<LegalCaseResponseDto> updateLegalCase(LegalCaseUpdateDto dto, long id){
        return legalCaseRepository.findById(id)
                .map(legalCase -> {
                    if (dto.getCitizenId() != null) {
                        Optional<CitizenEntity> citizen = citizenRepository.findById(dto.getCitizenId());
                        if (citizen.isEmpty()) {
                            return null;
                        }
                        legalCase.setCitizen(citizen.get());
                    }

                    if (dto.getCrimeId() != null) {
                        Optional<CrimeEntity> crime = crimeRepository.findById(dto.getCrimeId());
                        if (crime.isEmpty()) {
                            return null;
                        }
                        legalCase.setCrime(crime.get());
                    }

                    if (dto.getStartDate() != null) {
                        legalCase.setStartDate(dto.getStartDate());
                    }
                    if (dto.getEndDate() != null) {
                        legalCase.setEndDate(dto.getEndDate());
                    }
                    if (dto.getState() != null) {
                        legalCase.setState(dto.getState());
                    }

                    LegalCaseEntity saved = legalCaseRepository.save(legalCase);
                    return new LegalCaseResponseDto(saved.getId(), saved.getStartDate(), saved.getEndDate(), saved.getState());
                });
    }
}
