package com.javautn.roma.province.controller;

import com.javautn.roma.province.dto.ProvinceCreateDto;
import com.javautn.roma.province.dto.ProvinceResponceDto;
import com.javautn.roma.province.entity.ProvinceEntity;
import com.javautn.roma.province.service.ProvinceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/province")
public class ProvinceController {

    private final ProvinceService provinceService;

    public ProvinceController (ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProvinceResponceDto>> getAllProvinces() {
        return ResponseEntity.ok(provinceService.getAllProvinces());
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<ProvinceResponceDto> getOneProvince(@PathVariable final long id) {
        return provinceService.getOneProvince(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<ProvinceResponceDto> createProvince(@Valid @RequestBody final ProvinceCreateDto dto) {
        ProvinceResponceDto province = provinceService.createProvince(dto);

        return ResponseEntity.status(201).body(province);
    }
}
