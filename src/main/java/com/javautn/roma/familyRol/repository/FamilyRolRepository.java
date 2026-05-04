package com.javautn.roma.familyRol.repository;

import com.javautn.roma.familyRol.entity.FamilyRolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FamilyRolRepository extends JpaRepository<FamilyRolEntity, Long> {
    public List<FamilyRolEntity> findByCitizenId(Long citizenId);
}
