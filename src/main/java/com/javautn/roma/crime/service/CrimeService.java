package com.javautn.roma.crime.service;

import com.javautn.roma.crime.dto.CrimeCreateDto;
import com.javautn.roma.crime.entity.CrimeEntity;
import com.javautn.roma.crime.repository.CrimeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CrimeService {

    private final CrimeRepository crimeRepository;

    public CrimeService(CrimeRepository crimeRepository) {this.crimeRepository = crimeRepository; }

    public List<CrimeEntity> getAllCrime() {
        return crimeRepository.findAll();
    }

    public Optional<CrimeEntity> getOneCrime(long id) {
        return crimeRepository.findById(id);
    }

    public Optional<CrimeEntity> createCrime(CrimeCreateDto dto){
        if (crimeRepository.existsByDescriptionIgnoreCase(dto.getDescription())) {
            return Optional.empty();
        }

        CrimeEntity crime = new CrimeEntity(dto.getDescription());
        return Optional.of(crimeRepository.save(crime));
    }

    public Optional<CrimeEntity> updateCrime(CrimeCreateDto dto, long id) {
        if (crimeRepository.existsByDescriptionIgnoreCaseAndIdNot(dto.getDescription(), id)) {
            return Optional.empty();
        }

        return crimeRepository.findById(id)
                .map(crime -> {
                    crime.setDescription(dto.getDescription());
                    return crimeRepository.save(crime);
                });
    }
}
