package com.javautn.roma.crime.repository;

import com.javautn.roma.crime.entity.CrimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrimeRepository extends JpaRepository<CrimeEntity, Long> {
    boolean existsByDescription(String description);
    boolean existsByDescriptionAndIdNot(String name, Long id);
}