package com.javautn.roma.province.service;

import com.javautn.roma.province.dto.ProvinceCreateDto;
import com.javautn.roma.province.dto.ProvinceResponceDto;
import com.javautn.roma.province.entity.ProvinceEntity;
import com.javautn.roma.province.repository.ProvinceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<ProvinceResponceDto> getOneProvince(long id) {
        Optional<ProvinceEntity> province = provinceRepository.findById(id);
        return province.map(provinceEntity -> new ProvinceResponceDto(provinceEntity.getId(), provinceEntity.getName()));
    }

    public ProvinceResponceDto createProvince(ProvinceCreateDto dto) {
        ProvinceEntity province = new ProvinceEntity(dto.getName());

        ProvinceEntity savedProvince = provinceRepository.save(province);

        return new ProvinceResponceDto(
                savedProvince.getId(),
                savedProvince.getName()
        );
    }
}
