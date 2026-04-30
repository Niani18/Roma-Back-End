package com.javautn.roma.property.controller;

import com.javautn.roma.property.dto.PropertyCreateDto;
import com.javautn.roma.property.dto.PropertyResponseDto;
import com.javautn.roma.property.dto.PropertyWithOwnersDto;
import com.javautn.roma.property.entity.PropertyEntity;
import com.javautn.roma.property.service.PropertyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<PropertyWithOwnersDto> getOneWithOwners(@PathVariable long id) {
        return propertyService.findPropertyWithOwners(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
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
        return propertyService.findPropertyEntity(id).map(pr -> {
            PropertyResponseDto MIBOMBOOOO = new PropertyResponseDto(pr.getId(), pr.getName(), pr.getDescription());
            return ResponseEntity.ok(MIBOMBOOOO);
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/createProperty")
    public ResponseEntity<PropertyResponseDto> createProperty(@Valid @RequestBody PropertyCreateDto dto){
        PropertyEntity property = propertyService.createProperty(dto);
        return ResponseEntity.ok(new PropertyResponseDto(property.getId(), property.getName(), property.getDescription()));
    }


}
