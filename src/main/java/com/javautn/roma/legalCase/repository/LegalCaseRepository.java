package com.javautn.roma.legalCase.repository;

import com.javautn.roma.crime.entity.CrimeEntity;
import com.javautn.roma.legalCase.entity.LegalCaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LegalCaseRepository extends JpaRepository<LegalCaseEntity, Long> {

}
