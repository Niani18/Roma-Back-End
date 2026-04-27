package com.javautn.roma.tax.repository;

import com.javautn.roma.tax.entity.TaxEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxRepository extends JpaRepository<TaxEntity, Long> {
}
