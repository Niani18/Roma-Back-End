package com.javautn.roma.family.service;

import com.javautn.roma.family.dto.FamilyCreateDto;
import com.javautn.roma.family.dto.FamilyResponseDto;
import com.javautn.roma.family.entity.FamilyEntity;
import com.javautn.roma.family.repository.FamilyRepository;
import com.javautn.roma.province.dto.ProvinceResponseDto;
import com.javautn.roma.province.entity.ProvinceEntity;
import com.javautn.roma.province.repository.ProvinceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FamilyService {

    private final FamilyRepository familyRepository;
    private final ProvinceRepository provinceRepository;

    public FamilyService(FamilyRepository familyRepository, ProvinceRepository provinceRepository) {
        this.familyRepository = familyRepository;
        this.provinceRepository = provinceRepository;
    }

    public List<FamilyResponseDto> getAllFamily (){
        return familyRepository.findAll().stream()
                .map(family -> new FamilyResponseDto(family.getId(), family.getName(), ProvinceResponseDto.fromProvince(family.getProvince())))
                .toList();
    }

    public List<FamilyResponseDto> getAllFamily(long id) {
        return familyRepository.findByProvinceId(id).stream()
                .map(family -> new FamilyResponseDto(family.getId(), family.getName(), ProvinceResponseDto.fromProvince(family.getProvince())))
                .toList();
    }

    public Optional<FamilyResponseDto> getOneFamily(Long id){
        return familyRepository.findById(id)
                .map(fam -> new FamilyResponseDto(fam.getId(), fam.getName(),  ProvinceResponseDto.fromProvince(fam.getProvince())));
    }

    public Optional<FamilyResponseDto> createFamily (FamilyCreateDto dto){
        Optional<ProvinceEntity> province = provinceRepository.findById(dto.getProvinceId());

        if(province.isPresent()){
            FamilyEntity fam = new FamilyEntity(dto.getName(),  province.get());
            familyRepository.save(fam);
            return Optional.of(new FamilyResponseDto(fam.getId(), fam.getName(),  ProvinceResponseDto.fromProvince(fam.getProvince())));
        }
        else {
            return  Optional.empty();
        }
    }


}
