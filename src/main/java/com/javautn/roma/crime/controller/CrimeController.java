package com.javautn.roma.crime.controller;

import com.javautn.roma.crime.dto.CrimeCreateDto;
import com.javautn.roma.crime.dto.CrimeResponseDto;
import com.javautn.roma.crime.service.CrimeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crime")
public class CrimeController {

    private final CrimeService crimeService;

    public CrimeController(CrimeService crimeService) { this.crimeService = crimeService; }

    @GetMapping("/getAll")
    public ResponseEntity<List<CrimeResponseDto>> getAllCrime() {
        return ResponseEntity.ok(crimeService.getAllCrime().stream()
                .map(CrimeResponseDto::fromCrime)
                .toList());
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<CrimeResponseDto> getOneCrime(@PathVariable final long id) {
        return crimeService.getOneCrime(id)
                .map(CrimeResponseDto::fromCrime)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<CrimeResponseDto> createCrime(@Valid @RequestBody final CrimeCreateDto dto) {
        return crimeService.createCrime(dto)
                .map(CrimeResponseDto::fromCrime)
                .map(crime -> ResponseEntity.status(201).body(crime))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CrimeResponseDto> updateCrime(@Valid @RequestBody final CrimeCreateDto dto, @PathVariable final long id) {
        return crimeService.updateCrime(dto, id)
                .map(CrimeResponseDto::fromCrime)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
