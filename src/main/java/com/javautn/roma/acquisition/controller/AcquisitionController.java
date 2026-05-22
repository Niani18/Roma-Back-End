package com.javautn.roma.acquisition.controller;

import com.javautn.roma.acquisition.dto.AcquisitionCreateDto;
import com.javautn.roma.acquisition.dto.AcquisitionResponseDto;
import com.javautn.roma.acquisition.service.AcquisitionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acquisition")
public class AcquisitionController {

    private final AcquisitionService acquisitionService;

    public AcquisitionController(AcquisitionService acquisitionService) {
        this.acquisitionService = acquisitionService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AcquisitionResponseDto> getAcquisition(@Valid @RequestBody AcquisitionCreateDto dto) {
        return ResponseEntity.ok(AcquisitionResponseDto.toDto(acquisitionService.createAcquisition(dto)));
    }

}
