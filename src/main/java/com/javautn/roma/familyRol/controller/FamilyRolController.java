package com.javautn.roma.familyRol.controller;

import com.javautn.roma.family.dto.FamilyResponseDto;
import com.javautn.roma.familyRol.dto.FamilyRolCreateDto;
import com.javautn.roma.familyRol.dto.FamilyRolResponseDto;
import com.javautn.roma.familyRol.entity.FamilyRolEntity;
import com.javautn.roma.familyRol.service.FamilyRolService;
import com.javautn.roma.human.dto.CitizenResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/familyRole")
public class FamilyRolController {

    private final FamilyRolService familyRolService;

    public FamilyRolController(FamilyRolService familyRolService) {
        this.familyRolService = familyRolService;
    }

    @PostMapping("/createFamilyRole")
    public ResponseEntity<FamilyRolResponseDto> createFamilyRole (@Valid @RequestBody FamilyRolCreateDto dto){
        return familyRolService.crateFamilyRol(dto)
                .map(this::toResponseDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    private FamilyRolResponseDto toResponseDto(FamilyRolEntity role) {
        return new FamilyRolResponseDto(
                role.getId(),
                role.getRole(),
                CitizenResponseDTO.fromCitizen(role.getCitizen()),
                FamilyResponseDto.fromFamily(role.getFamily()),
                role.getDateOfJoining(),
                role.getDateOfUnjoining()
        );
    }
}
