package com.javautn.roma.family.contoller;

import com.javautn.roma.family.dto.FamilyCreateDto;
import com.javautn.roma.family.dto.FamilyResponseDto;
import com.javautn.roma.family.dto.FamilyWithPropertiesDto;
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
        return ResponseEntity.ok(familyService.getAllFamily());
    }

    @GetMapping("/getAllByProvince/{id}")
    public ResponseEntity<List<FamilyResponseDto>> getAllProvinces(@PathVariable long id) {
        return ResponseEntity.ok(familyService.getAllFamily(id));
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<FamilyResponseDto> getOne(@RequestParam long id) {
        return familyService.getOneFamily(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/getOneWithProperties/{id}")
    public ResponseEntity<FamilyWithPropertiesDto> getOneWithProperties(@PathVariable long id) {
        return familyService.getOneFamilyWithProperties(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<FamilyResponseDto> create(@Valid @RequestBody FamilyCreateDto dto){
        return familyService.createFamily(dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
