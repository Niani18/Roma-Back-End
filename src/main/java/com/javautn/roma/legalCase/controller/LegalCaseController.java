package com.javautn.roma.legalCase.controller;

import com.javautn.roma.legalCase.dto.LegalCaseCreateDto;
import com.javautn.roma.legalCase.dto.LegalCaseResponseDto;
import com.javautn.roma.legalCase.service.LegalCaseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/legalCase")

public class LegalCaseController {

    private final LegalCaseService legalCaseService;

    public LegalCaseController(LegalCaseService legalCaseService) {
        this.legalCaseService = legalCaseService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<LegalCaseResponseDto>> getAllLegalCases() {
        return  ResponseEntity.ok(legalCaseService.getAllLegalCase());
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<LegalCaseResponseDto> getOneLegalCase(@PathVariable final Long id) {
        return legalCaseService.getOneLegalCase(id)
                .map(ResponseEntity:: ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<LegalCaseResponseDto> createLegalCase(@Valid @RequestBody final LegalCaseCreateDto dto) {
        LegalCaseResponseDto legalCase = legalCaseService.createLegalCase(dto);
        return ResponseEntity.status(201).body(legalCase);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<LegalCaseResponseDto> updateLegalCase(@Valid @RequestBody final LegalCaseCreateDto dto, @PathVariable final Long id) {
        return legalCaseService.updateLegalCase(dto, id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
