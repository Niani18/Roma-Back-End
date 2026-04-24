package com.javautn.roma.province.service;

import com.javautn.roma.province.dto.ProvinceResponceDto;
import com.javautn.roma.province.entity.ProvinceEntity;
import com.javautn.roma.province.repository.ProvinceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProvinceService {

    private final ProvinceRepository provinceRepository;

    public ProvinceService(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    public List<ProvinceResponceDto> getAllProvinces() {
        return provinceRepository.findAll().stream()
                .map(province -> new ProvinceResponceDto(province.getId(), province.getName()))
                .toList();
        }

}
