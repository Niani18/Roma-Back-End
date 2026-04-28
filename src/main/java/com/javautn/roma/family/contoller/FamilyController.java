package com.javautn.roma.family.contoller;

import com.javautn.roma.family.dto.FamilyCreateDto;
import com.javautn.roma.family.dto.FamilyResponseDto;
import com.javautn.roma.family.entity.FamilyEntity;
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

    @GetMapping("/getOne/{id}")
    public ResponseEntity<FamilyResponseDto> getOne(@RequestParam long id) {
        return familyService.getOneFamily(id)
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
