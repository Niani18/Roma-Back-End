package com.javautn.roma.sentence.controller;

import com.javautn.roma.sentence.dto.SentenceCreateDto;
import com.javautn.roma.sentence.dto.SentenceResponseDto;
import com.javautn.roma.sentence.dto.SentenceUpdateDto;
import com.javautn.roma.sentence.entity.SentenceEntity;
import com.javautn.roma.sentence.service.SentenceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sentence")
public class SentenceController {

    private final SentenceService sentenceService;

    public SentenceController(SentenceService sentenceService) {
        this.sentenceService = sentenceService;
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<SentenceResponseDto>> getAllSentence() {
        return ResponseEntity.ok(sentenceService.getAllSentence().stream().map(SentenceResponseDto::fromEntity).toList());
    }

    @GetMapping("/getOne/{id}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and @authorizationService.canAccessSentence(#id))")
    public ResponseEntity<SentenceResponseDto> getSentenceById(@PathVariable final Long id) {
        return ResponseEntity.ok(SentenceResponseDto.fromEntity(sentenceService.getSentenceById(id)));
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SentenceResponseDto> createSentence(@Valid @RequestBody final SentenceCreateDto dto) {
        return ResponseEntity.ok(SentenceResponseDto.fromEntity(sentenceService.createSentence(dto)));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SentenceResponseDto> updateSentence(@Valid @RequestBody final SentenceUpdateDto dto, @PathVariable final Long id) {
        return ResponseEntity.ok(sentenceService.updateSentence(dto, id));
    }
}
