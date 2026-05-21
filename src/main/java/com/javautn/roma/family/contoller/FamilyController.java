package com.javautn.roma.family.contoller;

import com.javautn.roma.family.dto.*;
import com.javautn.roma.family.service.FamilyService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/family")
public class FamilyController {

    private final FamilyService familyService;

    public FamilyController(FamilyService familyService) {
        this.familyService = familyService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<FamilyResponseDto>> getAll() {
        return ResponseEntity.ok(familyService.getAllFamilies().stream()
                .map(FamilyResponseDto::fromFamily)
                .toList()
        );
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<FamilyResponseDto> getOne(@PathVariable long id) {
        return ResponseEntity.ok(FamilyResponseDto.fromFamily(familyService.getOneFamily(id)));
    }

    @GetMapping("/getOneWithProperties/{id}")
    public ResponseEntity<FamilyWithPropertiesDto> getOneWithProperties(@PathVariable long id) {
        return ResponseEntity.ok(FamilyWithPropertiesDto.fromFamily(familyService.getFamilyWithProperties(id)));
    }

    @GetMapping("/getOneWithMembers/{id}")
    public ResponseEntity<FamilyWithMembersDto> getOneWithMembers(@PathVariable long id) {
        return ResponseEntity.ok(FamilyWithMembersDto.fromFamily(familyService.getOneFamilyWithMembers(id)));
    }

    @GetMapping("/listTaxes/{id}")
    public ResponseEntity<FamilyTaxAssignationsResponseDTO> listTaxes(@PathVariable final long id) {
        return ResponseEntity.ok(FamilyTaxAssignationsResponseDTO.fromFamily(
                familyService.getFamilyWithTaxAssignations(id)));
    }

    @GetMapping("/getAllByProvince/{id}")
    public ResponseEntity<List<FamilyResponseDto>> getAllByProvince(@PathVariable long id) {
        return ResponseEntity.ok(familyService.getFamiliesByProvince(id).stream()
                .map(FamilyResponseDto::fromFamily)
                .toList());
    }

    @GetMapping("/getOneFamilyWithSlaves/{id}")
    public ResponseEntity<FamilyWithSlavesDto> getOneFamilyWithSlaves(@PathVariable long id) {
        return ResponseEntity.ok(FamilyWithSlavesDto.fromFamily(familyService.getOneFamilyWithSlaves(id)));
    }

    @PostMapping("/create")
    public ResponseEntity<FamilyResponseDto> create(@Valid @RequestBody FamilyCreateDto dto){
        return ResponseEntity.ok(FamilyResponseDto.fromFamily(familyService.createFamily(dto)));
    }
}
