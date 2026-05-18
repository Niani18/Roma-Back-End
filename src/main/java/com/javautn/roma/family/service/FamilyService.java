package com.javautn.roma.family.service;

import com.javautn.roma.family.dto.FamilyCreateDto;
import com.javautn.roma.family.entity.FamilyEntity;
import com.javautn.roma.family.repository.FamilyRepository;
import com.javautn.roma.province.entity.ProvinceEntity;
import com.javautn.roma.province.repository.ProvinceRepository;
import com.javautn.roma.shared.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (!provinceRepository.existsById(id)) {
            throw new NotFoundException("Province not found with id " + id);
        }
        return familyRepository.findByProvinceId(id);
    }

    public FamilyEntity getOneFamily(Long id){
        return familyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Family not found with id " + id));
    }


    public FamilyEntity getOneFamilyWithMembers(long id) {
        return familyRepository.findFamilyWithMembers(id)
                .orElseThrow(() -> new NotFoundException("Family not found with id " + id));
    }

    public FamilyEntity getOneFamilyWithSlaves(long id) {
        return familyRepository.findFamilyWithSlaves(id)
                .orElseThrow(() -> new NotFoundException("Family not found with id " + id));
    }

    public FamilyEntity getFamilyWithProperties(long id) {
        return familyRepository.findFamilyWithProperties(id)
                .orElseThrow(() -> new NotFoundException("Family not found with id " + id));
    }

    public FamilyEntity getFamilyWithTaxAssignations(long id) {
        return familyRepository.findFamilyWithTaxAssignations(id)
                .orElseThrow(() -> new NotFoundException("Family not found with id " + id));
    }

    public FamilyEntity createFamily (FamilyCreateDto dto){
        ProvinceEntity province = provinceRepository.findById(dto.getProvinceId())
                .orElseThrow(() -> new NotFoundException("Province not found with id " + dto.getProvinceId()));
        FamilyEntity fam = new FamilyEntity(dto.getName(), province);
        return familyRepository.save(fam);
    }


}
