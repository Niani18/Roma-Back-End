package com.javautn.roma.property.controller;

import com.javautn.roma.property.dto.PropertyCreateDto;
import com.javautn.roma.property.dto.PropertyResponseDto;
import com.javautn.roma.property.dto.PropertyTaxAssignationsResponseDTO;
import com.javautn.roma.property.dto.PropertyWithAllHoldingsDto;
import com.javautn.roma.property.dto.PropertyWithOwnersDto;
import com.javautn.roma.property.entity.PropertyEntity;
import com.javautn.roma.property.service.PropertyService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/property")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping("/getAllWithOwners")
    public ResponseEntity<List<PropertyWithOwnersDto>> getAllWithOwners() {
        return ResponseEntity.ok(propertyService.findAllPropertyWithOwners());
    }

    @GetMapping("/getOneWithOwners/{id}")
    public ResponseEntity<PropertyWithOwnersDto> getOneWithOwner(@PathVariable long id) {
        return ResponseEntity.ok(propertyService.findPropertyWithOwners(id));
    }

    @GetMapping("/getAllHolding/{id}")
    public ResponseEntity<PropertyWithAllHoldingsDto> getAllHolding(@PathVariable long id) {
        return ResponseEntity.ok(propertyService.findOnePropertyWithAllHoldings(id));
    }

    @GetMapping("/listTaxes/{id}")
    public ResponseEntity<PropertyTaxAssignationsResponseDTO> listTaxes(@PathVariable final long id) {
        return ResponseEntity.ok(PropertyTaxAssignationsResponseDTO.fromProperty(
                propertyService.findPropertyWithTaxAssignations(id)));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PropertyResponseDto>> getAll() {
        return ResponseEntity.ok(propertyService.findAllPropertyEntities().stream()
                .map(pr -> {
                    return new PropertyResponseDto(pr.getId(), pr.getName(), pr.getDescription());
                }).toList());
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<PropertyResponseDto> getOneProperty(@PathVariable long id) {
        PropertyEntity property = propertyService.findPropertyEntity(id);
        return ResponseEntity.ok(PropertyResponseDto.fromProperty(property));
    }

    @PostMapping("/createProperty")
    public ResponseEntity<PropertyResponseDto> createProperty(@Valid @RequestBody PropertyCreateDto dto){
        PropertyEntity property = propertyService.createProperty(dto);
        return ResponseEntity.ok(new PropertyResponseDto(property.getId(), property.getName(), property.getDescription()));
    }


}
