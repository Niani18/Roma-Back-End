package com.javautn.roma.family.contoller;

import com.javautn.roma.family.dto.FamilyCreateDto;
import com.javautn.roma.family.dto.FamilyResponseDto;
import com.javautn.roma.family.dto.FamilyTaxAssignationsResponseDTO;
import com.javautn.roma.family.entity.FamilyEntity;
import com.javautn.roma.family.service.FamilyService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/family")
public class FamilyController {

    private final FamilyService familyService;

    public FamilyController(FamilyService familyService) {
        this.familyService = familyService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<FamilyResponseDto>> getAll() {
        return ResponseEntity.ok(familyService.getAllFamilies().stream().map(FamilyResponseDto::fromFamily).toList());
    }

    @GetMapping("/listTaxes/{id}")
    public ResponseEntity<FamilyTaxAssignationsResponseDTO> listTaxes(@PathVariable("id") final long id) {
        return ResponseEntity.ofNullable(null);
    }

    @GetMapping("/getAllByProvince/{id}")
    public ResponseEntity<List<FamilyResponseDto>> getAllByProvince(@PathVariable long id) {
        return ResponseEntity.ok(familyService.getFamiliesByProvince(id).stream().map(FamilyResponseDto::fromFamily).toList());
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<FamilyResponseDto> getOne(@RequestParam long id) {
        return ResponseEntity.ofNullable(familyService.getOneFamily(id).map(FamilyResponseDto::fromFamily).orElse(null));
    }

    @PostMapping("/create")
    public ResponseEntity<FamilyResponseDto> create(@Valid @RequestBody FamilyCreateDto dto){
        return ResponseEntity.ofNullable(
                familyService.createFamily(dto).map(FamilyResponseDto::fromFamily)
                .orElse(null));
    }

}
