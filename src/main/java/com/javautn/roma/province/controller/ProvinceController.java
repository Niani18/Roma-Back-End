package com.javautn.roma.province.controller;

import com.javautn.roma.province.dto.ProvinceCreateDto;
import com.javautn.roma.province.dto.ProvinceResponseDto;
import com.javautn.roma.province.service.ProvinceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/province")
public class ProvinceController {

    private final ProvinceService provinceService;

    public ProvinceController (ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProvinceResponseDto>> getAllProvinces() {
        return ResponseEntity.ok(provinceService.getAllProvinces());
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<ProvinceResponseDto> getOneProvince(@PathVariable final long id) {
        return provinceService.getOneProvince(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<ProvinceResponseDto> createProvince(@Valid @RequestBody final ProvinceCreateDto dto) {
        ProvinceResponseDto province = provinceService.createProvince(dto);
        return ResponseEntity.status(201).body(province);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProvinceResponseDto> updateProvince(@Valid @RequestBody final ProvinceCreateDto dto, @PathVariable final long id) {
        return provinceService.updateProvince(dto, id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
