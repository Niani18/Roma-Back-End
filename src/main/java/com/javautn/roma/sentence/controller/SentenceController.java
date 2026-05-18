package com.javautn.roma.sentence.controller;

import com.javautn.roma.sentence.dto.SentenceCreateDto;
import com.javautn.roma.sentence.dto.SentenceResponseDto;
import com.javautn.roma.sentence.entity.SentenceEntity;
import com.javautn.roma.sentence.service.SentenceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<SentenceEntity>> getAllSentence() {
        return ResponseEntity.ok(sentenceService.getAllSentence());
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<SentenceEntity> getSentenceById(@PathVariable final Long id) {
        return ResponseEntity.ok(sentenceService.getSentenceById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<SentenceEntity> createSentence(@Valid @RequestBody final SentenceCreateDto dto) {
        return ResponseEntity.status(201).body(sentenceService.createSentence(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SentenceResponseDto> updateSentence(@Valid @RequestBody final SentenceCreateDto dto, @PathVariable final Long id) {
        return ResponseEntity.ok(sentenceService.updateSentence(dto, id));
    }
}
