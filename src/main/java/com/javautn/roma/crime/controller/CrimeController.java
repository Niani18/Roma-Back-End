package com.javautn.roma.crime.controller;

import com.javautn.roma.crime.dto.CrimeCreateDto;
import com.javautn.roma.crime.dto.CrimeResponseDto;
import com.javautn.roma.crime.service.CrimeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crime")
public class CrimeController {

    private final CrimeService crimeService;

    public CrimeController(CrimeService crimeService) { this.crimeService = crimeService; }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CrimeResponseDto>> getAllCrime() {
        return ResponseEntity.ok(crimeService.getAllCrime().stream()
                .map(CrimeResponseDto::fromCrime)
                .toList());
    }

    @GetMapping("/getOne/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CrimeResponseDto> getOneCrime(@PathVariable final long id) {
        return ResponseEntity.ok(CrimeResponseDto.fromCrime(crimeService.getOneCrime(id)));
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CrimeResponseDto> createCrime(@Valid @RequestBody final CrimeCreateDto dto) {
        return ResponseEntity.status(201).body(CrimeResponseDto.fromCrime(crimeService.createCrime(dto)));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CrimeResponseDto> updateCrime(@Valid @RequestBody final CrimeCreateDto dto, @PathVariable final long id) {
        return ResponseEntity.ok(CrimeResponseDto.fromCrime(crimeService.updateCrime(dto, id)));
    }
}
