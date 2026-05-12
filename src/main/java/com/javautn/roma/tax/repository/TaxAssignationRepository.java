package com.javautn.roma.tax.repository;

import com.javautn.roma.tax.entity.TaxAssignationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxAssignationRepository extends JpaRepository<TaxAssignationEntity, Long> {
}
