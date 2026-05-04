package com.javautn.roma.family.service;

import com.javautn.roma.family.dto.FamilyCreateDto;
import com.javautn.roma.family.dto.FamilyWithPropertiesDto;
import com.javautn.roma.family.entity.FamilyEntity;
import com.javautn.roma.family.repository.FamilyRepository;
import com.javautn.roma.holding.entity.HoldingEntity;
import com.javautn.roma.property.dto.PropertyResponseDto;
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

    public List<FamilyEntity> getAllFamily (){
        return familyRepository.findAll();
    }

    public List<FamilyEntity> getAllFamily(long id) {
        return familyRepository.findByProvinceId(id);
    }

    public Optional<FamilyEntity> getOneFamily(Long id){
        return familyRepository.findById(id);
    }

    public Optional<FamilyWithPropertiesDto> getOneFamilyWithProperties(long id) {
        return familyRepository.findFamilyWithProperties(id)
                .map(this::toFamilyWithPropertiesDto);
    }

    public Optional<FamilyEntity> getOneFamilyWithMembers(long id) {
        return familyRepository.findFamilyWithMembers(id);
    }

    public Optional<FamilyEntity> createFamily (FamilyCreateDto dto){
        Optional<ProvinceEntity> province = provinceRepository.findById(dto.getProvinceId());
        if(province.isPresent()){
            FamilyEntity fam = new FamilyEntity(dto.getName(),  province.get());
            familyRepository.save(fam);
            return Optional.of(fam);
        }
        else {
            return  Optional.empty();
        }
    }



    private FamilyWithPropertiesDto toFamilyWithPropertiesDto(FamilyEntity family) {
        List<PropertyResponseDto> properties = family.getHoldings().stream()
                .map(HoldingEntity::getProperty)
                .distinct()
                .map(PropertyResponseDto::fromProperty)
                .toList();

        return new FamilyWithPropertiesDto(
                family.getId(),
                family.getName(),
                ProvinceResponseDto.fromProvince(family.getProvince()),
                properties
        );
    }

}
