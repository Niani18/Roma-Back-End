package com.javautn.roma.province.service;

import com.javautn.roma.province.dto.ProvinceCreateDto;
import com.javautn.roma.province.dto.ProvinceResponseDto;
import com.javautn.roma.province.entity.ProvinceEntity;
import com.javautn.roma.province.repository.ProvinceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinceService {

    private final ProvinceRepository provinceRepository;

    public ProvinceService(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    public List<ProvinceResponseDto> getAllProvinces() {
        return provinceRepository.findAll().stream()
                .map(province -> new ProvinceResponseDto(province.getId(), province.getName()))
                .toList();
        }

    public Optional<ProvinceResponseDto> getOneProvince(long id) {
        return provinceRepository.findById(id)
                .map(provinceEntity -> new ProvinceResponseDto(provinceEntity.getId(), provinceEntity.getName()));
    }

    public ProvinceResponseDto createProvince(ProvinceCreateDto dto) {
        ProvinceEntity province = new ProvinceEntity(dto.getName());
        ProvinceEntity savedProvince = provinceRepository.save(province);

        return new ProvinceResponseDto(savedProvince.getId(), savedProvince.getName());
    }

    public Optional<ProvinceResponseDto> updateProvince(ProvinceCreateDto dto, long id) {
        return provinceRepository.findById(id)
                .map(province -> {
                    province.setName(dto.getName());
                    ProvinceEntity saved = provinceRepository.save(province);
                    return new ProvinceResponseDto(saved.getId(), saved.getName());
                });
    }

}
