package com.javautn.roma.province.controller;

import com.javautn.roma.province.dto.ProvinceResponceDto;
import com.javautn.roma.province.entity.ProvinceEntity;
import com.javautn.roma.province.service.ProvinceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

}
