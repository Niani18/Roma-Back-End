package com.javautn.roma.sentence.service;

import com.javautn.roma.legalCase.entity.LegalCaseEntity;
import com.javautn.roma.legalCase.repository.LegalCaseRepository;
import com.javautn.roma.sentence.dto.SentenceCreateDto;
import com.javautn.roma.sentence.dto.SentenceResponseDto;
import com.javautn.roma.sentence.entity.SentenceEntity;
import com.javautn.roma.sentence.repository.SentenceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SentenceService {
    private final SentenceRepository sentenceRepository;
    private final  LegalCaseRepository legalCaseRepository;

    public  SentenceService(SentenceRepository sentenceRepository, LegalCaseRepository legalCaseRepository) {
        this.sentenceRepository = sentenceRepository;
        this.legalCaseRepository = legalCaseRepository;
    }

    public List<SentenceEntity> getAllSentence() { return  new ArrayList<SentenceEntity>(); }

    public Optional<SentenceEntity> getSentenceById(Long id) { return sentenceRepository.findById(id); }

    public Optional<SentenceEntity> createSentence(final SentenceCreateDto dto) {
        LegalCaseEntity legalCase = legalCaseRepository.findById(dto.getIdLegalCase())
                .orElseThrow(() -> new RuntimeException("Legal case no encontrado"));

        SentenceEntity sentence = new SentenceEntity(
                dto.getDescription(),
                dto.getApplicationDate(),
                dto.getEstimatedEndDate(),
                null
        );

        sentence.setLegalCase(legalCase);

        return Optional.of(sentenceRepository.save(sentence));
    }

    public Optional<SentenceResponseDto> updateSentence(SentenceCreateDto dto,  Long id) {
        return sentenceRepository.findById(id)
                .map(sentence -> {
                    sentence.setDescription(dto.getDescription());
                    sentence.setApplicationDate(dto.getApplicationDate());
                    sentence.setEstimatedEndDate(dto.getEstimatedEndDate());

                    if (sentence.getFinalEndDate() == null) {
                        sentence.setFinalEndDate(new Date());
                    }

                    SentenceEntity saved = sentenceRepository.save(sentence);
                    return new SentenceResponseDto(saved.getId(), saved.getDescription(), saved.getLegalCase().getId(), saved.getEstimatedEndDate(), saved.getEstimatedEndDate(), saved.getFinalEndDate());
                });
    }
}
