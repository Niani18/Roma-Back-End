package com.javautn.roma.province.service;

import com.javautn.roma.province.dto.ProvinceCreateDto;
import com.javautn.roma.province.dto.ProvinceResponseDto;
import com.javautn.roma.province.entity.ProvinceEntity;
import com.javautn.roma.province.repository.ProvinceRepository;
import com.javautn.roma.shared.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceService {

    private final ProvinceRepository provinceRepository;

    public ProvinceService(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    public List<ProvinceResponseDto> getAllProvinces() {
        return provinceRepository.findAll().stream()
                .map(province -> new ProvinceResponseDto(province.getId(), province.getName()/*, province.getFamilies())*/))
                .toList();
        }

    public ProvinceResponseDto getOneProvince(long id) {
        ProvinceEntity province = provinceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Province not found with id " + id));
        return new ProvinceResponseDto(province.getId(), province.getName()/*,  provinceEntity.getFamilies())*/);
    }

    public ProvinceResponseDto createProvince(ProvinceCreateDto dto) {
        ProvinceEntity province = new ProvinceEntity(dto.getName());
        ProvinceEntity savedProvince = provinceRepository.save(province);

        return new ProvinceResponseDto(savedProvince.getId(), savedProvince.getName()/*,  savedProvince.getFamilies()*/);
    }

    public ProvinceResponseDto updateProvince(ProvinceCreateDto dto, long id) {
        ProvinceEntity province = provinceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Province not found with id " + id));
        province.setName(dto.getName());
        ProvinceEntity saved = provinceRepository.save(province);
        return new ProvinceResponseDto(saved.getId(), saved.getName()/*,  saved.getFamilies()*/);
    }

}
