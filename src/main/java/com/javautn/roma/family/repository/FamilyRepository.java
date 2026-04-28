package com.javautn.roma.family.repository;

import com.javautn.roma.family.entity.FamilyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FamilyRepository extends JpaRepository<FamilyEntity, Long> {
    List<FamilyEntity> findByProvinceId(long provinceId);
}
