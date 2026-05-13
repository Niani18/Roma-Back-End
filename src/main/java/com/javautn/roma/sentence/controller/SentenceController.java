package com.javautn.roma.sentence.controller;

import com.javautn.roma.sentence.dto.SentenceCreateDto;
import com.javautn.roma.sentence.dto.SentenceResponseDto;
import com.javautn.roma.sentence.dto.SentenceUpdateDto;
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
    public ResponseEntity<List<SentenceResponseDto>> getAllSentence() {
        return ResponseEntity.ok(sentenceService.getAllSentence());
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<SentenceResponseDto> getSentenceById(@PathVariable final Long id) {
        return sentenceService.getSentenceById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<SentenceResponseDto> createSentence(@Valid @RequestBody final SentenceCreateDto dto) {
        return sentenceService.createSentence(dto)
                .map(sentence -> ResponseEntity.status(201).body(sentence))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<SentenceResponseDto> updateSentence(@Valid @RequestBody final SentenceUpdateDto dto, @PathVariable final Long id) {
        return sentenceService.updateSentence(dto, id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
