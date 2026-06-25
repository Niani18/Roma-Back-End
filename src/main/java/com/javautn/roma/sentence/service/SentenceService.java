package com.javautn.roma.sentence.service;

import com.javautn.roma.legalCase.entity.LegalCaseEntity;
import com.javautn.roma.legalCase.repository.LegalCaseRepository;
import com.javautn.roma.sentence.dto.SentenceCreateDto;
import com.javautn.roma.sentence.dto.SentenceResponseDto;
import com.javautn.roma.sentence.dto.SentenceUpdateDto;
import com.javautn.roma.sentence.entity.SentenceEntity;
import com.javautn.roma.sentence.repository.SentenceRepository;
import com.javautn.roma.shared.exception.ConflictException;
import com.javautn.roma.shared.exception.NotFoundException;
import org.springframework.stereotype.Service;

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

    public List<SentenceEntity> getAllSentence() { return sentenceRepository.findAll(); }

    public SentenceEntity getSentenceById(Long id) {
        return sentenceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sentence not found with id " + id));
    }

    public SentenceEntity createSentence(final SentenceCreateDto dto) {
        LegalCaseEntity legalCase = legalCaseRepository.findById(dto.getIdLegalCase())
                .orElseThrow(() -> new NotFoundException("Legal case not found with id " + dto.getIdLegalCase()));

        if (dto.getEstimatedEndDate() != null && dto.getEstimatedEndDate().before(dto.getApplicationDate())) {
            throw new ConflictException("The estimated end date cannot be before the application date");
        }

        SentenceEntity sentence = new SentenceEntity(
                dto.getDescription(),
                dto.getApplicationDate(),
                dto.getEstimatedEndDate(),
                null
        );

        sentence.setLegalCase(legalCase);

        return sentenceRepository.save(sentence);
    }

    public SentenceResponseDto updateSentence(SentenceUpdateDto dto, Long id) {
        SentenceEntity sentence = sentenceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sentence not found with id " + id));

        if (sentence.getFinalEndDate() == null) {
            Date today = new Date();

            if (today.before(sentence.getApplicationDate())) {
                throw new ConflictException("The final end date cannot be before the application date");
            }
            sentence.setFinalEndDate(today);
        }

        SentenceEntity saved = sentenceRepository.save(sentence);
        return new SentenceResponseDto(saved.getId(), saved.getDescription(), saved.getLegalCase().getId(),
                saved.getApplicationDate(), saved.getEstimatedEndDate(), saved.getFinalEndDate());
    }
}
