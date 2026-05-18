package com.javautn.roma.crime.service;

import com.javautn.roma.crime.dto.CrimeCreateDto;
import com.javautn.roma.crime.entity.CrimeEntity;
import com.javautn.roma.crime.repository.CrimeRepository;
import com.javautn.roma.shared.exception.ConflictException;
import com.javautn.roma.shared.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrimeService {

    private final CrimeRepository crimeRepository;

    public CrimeService(CrimeRepository crimeRepository) {this.crimeRepository = crimeRepository; }

    public List<CrimeEntity> getAllCrime() {
        return crimeRepository.findAll();
    }

    public CrimeEntity getOneCrime(long id) {
        return crimeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Crime not found with id " + id));
    }

    public CrimeEntity createCrime(CrimeCreateDto dto){
        if (crimeRepository.existsByDescriptionIgnoreCase(dto.getDescription())) {
            throw new ConflictException("Crime already exists with description '" + dto.getDescription() + "'");
        }

        CrimeEntity crime = new CrimeEntity(dto.getDescription());
        return crimeRepository.save(crime);
    }

    public CrimeEntity updateCrime(CrimeCreateDto dto, long id) {
        if (crimeRepository.existsByDescriptionIgnoreCaseAndIdNot(dto.getDescription(), id)) {
            throw new ConflictException("Crime already exists with description '" + dto.getDescription() + "'");
        }

        CrimeEntity crime = getOneCrime(id);
        crime.setDescription(dto.getDescription());
        return crimeRepository.save(crime);
    }
}
