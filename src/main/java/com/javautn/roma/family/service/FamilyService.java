package com.javautn.roma.family.service;

import com.javautn.roma.family.dto.FamilyCreateDto;
import com.javautn.roma.family.entity.FamilyEntity;
import com.javautn.roma.family.repository.FamilyRepository;
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

    public List<FamilyEntity> getAllFamilies(){
        return familyRepository.findAll();
    }

    public List<FamilyEntity> getFamiliesByProvince(long id) {
        return familyRepository.findByProvinceId(id);
    }

    public Optional<FamilyEntity> getOneFamily(Long id){
        return familyRepository.findById(id);
    }

    public Optional<FamilyEntity> createFamily (FamilyCreateDto dto){
        Optional<ProvinceEntity> province = provinceRepository.findById(dto.getProvinceId());
        if(province.isPresent()) {
            FamilyEntity fam = new FamilyEntity(dto.getName(), province.get());
            return Optional.of(familyRepository.saveAndFlush(fam));
        }
        else {
            return  Optional.empty();
        }
    }


    // por ahora no voy a usar update por no tener sentido de negocio
    // agregar buscar todas las propieades de la familia


}
