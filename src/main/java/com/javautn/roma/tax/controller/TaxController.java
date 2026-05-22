package com.javautn.roma.tax.controller;

import com.javautn.roma.tax.dto.TaxCreateDTO;
import com.javautn.roma.tax.dto.TaxResponseDTO;
import com.javautn.roma.tax.service.TaxService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/tax")
public class TaxController {

    private final TaxService taxService;

    public TaxController(final TaxService taxService) {
        this.taxService = taxService;
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TaxResponseDTO>> getAllTaxes() {
        return ResponseEntity.ok(taxService.getAllTaxes().stream()
                .map(TaxResponseDTO::fromTax)
                .toList());
    }

    @GetMapping("/getOne/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TaxResponseDTO> getOneById(
            @PathVariable final long id) {
        return ResponseEntity.ok(TaxResponseDTO.fromTax(taxService.getTaxById(id)));
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TaxResponseDTO> createTax(@Valid @RequestBody TaxCreateDTO dto) {
        return ResponseEntity.ok(TaxResponseDTO.fromTax(taxService.createTax(dto)));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TaxResponseDTO> updateTax(
            @PathVariable final long id,
            @RequestBody @Valid final TaxCreateDTO dto) {
        return ResponseEntity.ok(TaxResponseDTO.fromTax(taxService.updateTax(id, dto)));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TaxResponseDTO> deleteTax(
            @PathVariable final long id) {
        return ResponseEntity.ok(TaxResponseDTO.fromTax(taxService.deleteTax(id)));
    }
}
