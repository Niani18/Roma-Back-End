package com.javautn.roma.tax.controller;

import com.javautn.roma.tax.dto.TaxCreateDTO;
import com.javautn.roma.tax.dto.TaxResponseDTO;
import com.javautn.roma.tax.entity.TaxEntity;
import com.javautn.roma.tax.service.TaxService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tax")
public class TaxController {

    private final TaxService taxService;

    public TaxController(final TaxService taxService) {
        this.taxService = taxService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TaxResponseDTO>> getAllTaxes() {
        return ResponseEntity.ok(taxService.getAllTaxes().stream()
                .map(TaxResponseDTO::fromTax)
                .toList());
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<TaxResponseDTO> getOneById(
            @PathVariable final long id) {
        return taxService.getTaxById(id)
                .map(TaxResponseDTO::fromTax)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<TaxResponseDTO> createTax(@Valid @RequestBody TaxCreateDTO dto) {
        return taxService.createTax(dto)
                .map(TaxResponseDTO::fromTax)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TaxResponseDTO> updateTax(
            @PathVariable final long id,
            @RequestBody @Valid final TaxCreateDTO dto) {
        return ResponseEntity.of(taxService.updateTax(id, dto).map(TaxResponseDTO::fromTax));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TaxResponseDTO> deleteTax(
            @PathVariable final long id) {
        final Optional<TaxEntity> tax = taxService.getTaxById(id);
        taxService.deleteTax(id);
        return ResponseEntity.of(tax.map(TaxResponseDTO::fromTax));
    }
}
