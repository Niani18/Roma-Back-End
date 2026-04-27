package com.javautn.roma.tax.controller;

import com.javautn.roma.tax.dto.TaxCreateDTO;
import com.javautn.roma.tax.dto.TaxResponseDTO;
import com.javautn.roma.tax.entity.TaxEntity;
import com.javautn.roma.tax.service.TaxService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<TaxEntity>> getAllTaxes() {
        return ResponseEntity.ok(taxService.getAllTaxes());
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<TaxResponseDTO> getOneById(
            @PathVariable("id") final long id) {
        return ResponseEntity.of(taxService.getTaxById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<TaxResponseDTO> createTax(final TaxCreateDTO dto) {
        return ResponseEntity.of(taxService.createTax(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TaxResponseDTO> updateTax(
            @PathVariable("id") final long id,
            @RequestBody @Valid final TaxCreateDTO dto) {
        return ResponseEntity.of(taxService.updateTax(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TaxResponseDTO> deleteTax(
            @PathVariable("id") final long id) {
        return ResponseEntity.of(taxService.deleteTax(id));
    }
}
