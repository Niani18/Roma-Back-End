package com.javautn.roma.legalCase.service;

import com.javautn.roma.legalCase.dto.LegalCaseCreateDto;
import com.javautn.roma.legalCase.dto.LegalCaseResponseDto;
import com.javautn.roma.legalCase.entity.LegalCaseEntity;
import com.javautn.roma.legalCase.repository.LegalCaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LegalCaseService {

    private final  LegalCaseRepository legalCaseRepository;

    public LegalCaseService(LegalCaseRepository legalCaseRepository) {
        this.legalCaseRepository = legalCaseRepository;
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
        LegalCaseEntity legalCase = new LegalCaseEntity(dto.getStartDate(), dto.getEndDate(), dto.getState());
        LegalCaseEntity savedLegalCase = legalCaseRepository.save(legalCase);

        return new LegalCaseResponseDto(savedLegalCase.getId(), savedLegalCase.getStartDate(), savedLegalCase.getEndDate(), savedLegalCase.getState());
    }

    public Optional<LegalCaseResponseDto> updateLegalCase(LegalCaseCreateDto dto, long id){
        return legalCaseRepository.findById(id)
                .map(legalCase -> {legalCase.setStartDate(dto.getStartDate()); legalCase.setEndDate(dto.getEndDate()); legalCase.setState(dto.getState());
                LegalCaseEntity saved =  legalCaseRepository.save(legalCase);
                return new LegalCaseResponseDto(saved.getId(), saved.getStartDate(), saved.getEndDate(), saved.getState());
                });
    }
}
