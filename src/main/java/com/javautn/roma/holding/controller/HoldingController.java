package com.javautn.roma.holding.controller;

import com.javautn.roma.family.dto.FamilyResponseDto;
import com.javautn.roma.holding.dto.HoldingCreateDto;
import com.javautn.roma.holding.dto.HoldingResponseDto;
import com.javautn.roma.holding.entity.HoldingEntity;
import com.javautn.roma.holding.service.HoldingService;
import com.javautn.roma.property.dto.PropertyResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/holding")
public class HoldingController {

    private final HoldingService holdingService;

    public HoldingController(HoldingService holdingService) {
        this.holdingService = holdingService;
    }

    @PostMapping("/create")
    public ResponseEntity<HoldingResponseDto> createHolding (@Valid @RequestBody HoldingCreateDto dto){

        HoldingEntity hold = holdingService.saveHolding(dto);
        return ResponseEntity.ok(new HoldingResponseDto(hold.getId(), PropertyResponseDto.fromProperty(hold.getProperty()), FamilyResponseDto.fromFamily(hold.getFamily()), hold.getPrice(), hold.getDate()));

    }

}
