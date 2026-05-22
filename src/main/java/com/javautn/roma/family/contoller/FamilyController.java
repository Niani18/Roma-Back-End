package com.javautn.roma.family.contoller;

import com.javautn.roma.family.dto.*;
import com.javautn.roma.family.service.FamilyService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<FamilyResponseDto>> getAll() {
        return ResponseEntity.ok(familyService.getAllFamilies().stream()
                .map(FamilyResponseDto::fromFamily)
                .toList()
        );
    }

    @GetMapping("/getOne/{id}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and @authorizationService.canAccessFamily(#id))")
    public ResponseEntity<FamilyResponseDto> getOne(@PathVariable long id) {
        return ResponseEntity.ok(FamilyResponseDto.fromFamily(familyService.getOneFamily(id)));
    }

    @GetMapping("/getOneWithProperties/{id}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and @authorizationService.canAccessFamily(#id))")
    public ResponseEntity<FamilyWithPropertiesDto> getOneWithProperties(@PathVariable long id) {
        return ResponseEntity.ok(FamilyWithPropertiesDto.fromFamily(familyService.getFamilyWithProperties(id)));
    }

    @GetMapping("/getOneWithMembers/{id}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and @authorizationService.canAccessFamily(#id))")
    public ResponseEntity<FamilyWithMembersDto> getOneWithMembers(@PathVariable long id) {
        return ResponseEntity.ok(FamilyWithMembersDto.fromFamily(familyService.getOneFamilyWithMembers(id)));
    }

    @GetMapping("/listTaxes/{id}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and @authorizationService.canAccessFamily(#id))")
    public ResponseEntity<FamilyTaxAssignationsResponseDTO> listTaxes(@PathVariable final long id) {
        return ResponseEntity.ok(FamilyTaxAssignationsResponseDTO.fromFamily(
                familyService.getFamilyWithTaxAssignations(id)));
    }

    @GetMapping("/getAllByProvince/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<FamilyResponseDto>> getAllByProvince(@PathVariable long id) {
        return ResponseEntity.ok(familyService.getFamiliesByProvince(id).stream()
                .map(FamilyResponseDto::fromFamily)
                .toList());
    }

    @GetMapping("/getOneFamilyWithSlaves/{id}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and @authorizationService.canAccessFamily(#id))")
    public ResponseEntity<FamilyWithSlavesDto> getOneFamilyWithSlaves(@PathVariable long id) {
        return ResponseEntity.ok(FamilyWithSlavesDto.fromFamily(familyService.getOneFamilyWithSlaves(id)));
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FamilyResponseDto> create(@Valid @RequestBody FamilyCreateDto dto){
        return ResponseEntity.ok(FamilyResponseDto.fromFamily(familyService.createFamily(dto)));
    }
}
