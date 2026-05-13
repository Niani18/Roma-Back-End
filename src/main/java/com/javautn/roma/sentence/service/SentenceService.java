package com.javautn.roma.sentence.service;

import com.javautn.roma.legalCase.entity.LegalCaseEntity;
import com.javautn.roma.legalCase.repository.LegalCaseRepository;
import com.javautn.roma.sentence.dto.SentenceCreateDto;
import com.javautn.roma.sentence.dto.SentenceResponseDto;
import com.javautn.roma.sentence.dto.SentenceUpdateDto;
import com.javautn.roma.sentence.entity.SentenceEntity;
import com.javautn.roma.sentence.repository.SentenceRepository;
import org.springframework.stereotype.Service;

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

    public List<SentenceResponseDto> getAllSentence() {
        return  sentenceRepository.findAll().stream()
                .map(sentence -> new SentenceResponseDto(sentence.getId(), sentence.getDescription(), sentence.getLegalCase().getId(), sentence.getApplicationDate(), sentence.getEstimatedEndDate(), sentence.getFinalEndDate()))
                .toList();

    }

    public Optional<SentenceResponseDto> getSentenceById(Long id) {
        return sentenceRepository.findById(id)
                .map(this::toResponseDto);
    }

    public Optional<SentenceResponseDto> createSentence(final SentenceCreateDto dto) {
        LegalCaseEntity legalCase = legalCaseRepository.findById(dto.getIdLegalCase())
                .orElseThrow(() -> new RuntimeException("Legal case no encontrado"));

        SentenceEntity sentence = new SentenceEntity(
                dto.getDescription(),
                dto.getApplicationDate(),
                dto.getEstimatedEndDate(),
                null
        );

        sentence.setLegalCase(legalCase);

        SentenceEntity saved = sentenceRepository.save(sentence);
        return Optional.of(toResponseDto(saved));
    }

    public Optional<SentenceResponseDto> updateSentence(SentenceUpdateDto dto, Long id) {
        return sentenceRepository.findById(id)
                .map(sentence -> {
                    if (dto.getIdLegalCase() != null) {
                        Optional<LegalCaseEntity> legalCase = legalCaseRepository.findById(dto.getIdLegalCase());
                        if (legalCase.isEmpty()) {
                            return null;
                        }
                        sentence.setLegalCase(legalCase.get());
                    }

                    if (dto.getDescription() != null) {
                        sentence.setDescription(dto.getDescription());
                    }
                    if (dto.getApplicationDate() != null) {
                        sentence.setApplicationDate(dto.getApplicationDate());
                    }
                    if (dto.getEstimatedEndDate() != null) {
                        sentence.setEstimatedEndDate(dto.getEstimatedEndDate());
                    }
                    if (dto.getFinalEndDate() != null) {
                        sentence.setFinalEndDate(dto.getFinalEndDate());
                    }

                    SentenceEntity saved = sentenceRepository.save(sentence);
                    return toResponseDto(saved);
                });
    }

    private SentenceResponseDto toResponseDto(SentenceEntity sentence) {
        return new SentenceResponseDto(
                sentence.getId(),
                sentence.getDescription(),
                sentence.getLegalCase().getId(),
                sentence.getApplicationDate(),
                sentence.getEstimatedEndDate(),
                sentence.getFinalEndDate()
        );
    }
}
