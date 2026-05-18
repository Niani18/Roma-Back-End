package com.javautn.roma.tax.controller;

import com.javautn.roma.tax.dto.TaxAssignationFamilyCreateDTO;
import com.javautn.roma.tax.dto.TaxAssignationPropertyCreateDTO;
import com.javautn.roma.tax.dto.TaxAssignationResponseDTO;
import com.javautn.roma.tax.dto.TaxAssignationUpdateDTO;
import com.javautn.roma.tax.service.TaxAssignationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/taxAssignation")
public class TaxAssignationController {

    private final TaxAssignationService assignationService;
    public TaxAssignationController(
            TaxAssignationService assignationService) {
        this.assignationService = assignationService;
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<TaxAssignationResponseDTO>> getAll() {
        return ResponseEntity.ok(
                assignationService.getAll().stream()
                        .map(TaxAssignationResponseDTO::fromTaxAssignation).toList()
        );
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<TaxAssignationResponseDTO> getOneAssignation(@PathVariable long id) {
        return ResponseEntity.ok(TaxAssignationResponseDTO.fromTaxAssignation(assignationService.getOne(id)));
    }

    @PostMapping("/createForFamily")
    public ResponseEntity<TaxAssignationResponseDTO> createAssignationForFamily(
            @Valid @RequestBody TaxAssignationFamilyCreateDTO dto) {
        return ResponseEntity.ok(TaxAssignationResponseDTO.fromTaxAssignation(
                assignationService.createAssignationForFamily(dto)));
    }

    @PostMapping("/createForProperty")
    public ResponseEntity<TaxAssignationResponseDTO> createAssignationForProperty(
            @Valid @RequestBody TaxAssignationPropertyCreateDTO dto) {
        return ResponseEntity.ok(TaxAssignationResponseDTO.fromTaxAssignation(
                assignationService.createAssignationForProperty(dto)));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<TaxAssignationResponseDTO> updateTaxAssignation(
            @PathVariable long id, @Valid @RequestBody TaxAssignationUpdateDTO dto) {
        return ResponseEntity.ok(TaxAssignationResponseDTO.fromTaxAssignation(
                assignationService.updateTaxAssignation(id, dto)));
    }



}
