package com.javautn.roma.crime.service;

import com.javautn.roma.crime.dto.CrimeCreateDto;
import com.javautn.roma.crime.dto.CrimeResponseDto;
import com.javautn.roma.crime.entity.CrimeEntity;
import com.javautn.roma.crime.repository.CrimeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CrimeService {

    private final CrimeRepository crimeRepository;

    public CrimeService(CrimeRepository crimeRepository) {this.crimeRepository = crimeRepository; }

    public List<CrimeResponseDto> getAllCrime() {
        return  crimeRepository.findAll().stream()
                .map(crime -> new CrimeResponseDto(crime.getId(), crime.getDescription()))
                .toList();
    }

    public Optional<CrimeResponseDto> getOneCrime(long id) {
        Optional<CrimeEntity> crime = crimeRepository.findById(id);
        return crime.map(crimeEntity -> new CrimeResponseDto(crimeEntity.getId(), crimeEntity.getDescription()));
    }

    public CrimeResponseDto createCrime(CrimeCreateDto dto){
        if (crimeRepository.existsByDescription(dto.getDescription())){
            return null;
        }

        CrimeEntity crime = new CrimeEntity(dto.getDescription());
        CrimeEntity savedCrime = crimeRepository.save(crime);

        return new CrimeResponseDto(savedCrime.getId(), savedCrime.getDescription());
    }

    public Optional<CrimeResponseDto> updateCrime(CrimeCreateDto dto, long id) {
        if (crimeRepository.existsByDescriptionAndIdNot(dto.getDescription(), id)) {
            return Optional.empty();
        }

        return crimeRepository.findById(id)
                .map(crime -> {
                    crime.setDescription(dto.getDescription());
                    CrimeEntity saved = crimeRepository.save(crime);
                    return new CrimeResponseDto(saved.getId(), saved.getDescription());
                });
    }
}
