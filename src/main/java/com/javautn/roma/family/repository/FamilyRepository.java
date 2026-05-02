package com.javautn.roma.family.repository;

import com.javautn.roma.family.entity.FamilyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FamilyRepository extends JpaRepository<FamilyEntity, Long> {
    List<FamilyEntity> findByProvinceId(long provinceId);

    @Query("""
            select distinct f
            from FamilyEntity f
            inner join fetch f.holding h
            inner join fetch h.property
            where f.id = :id
              and h.state = com.javautn.roma.holding.entity.HoldingState.ACTIVE
            """)
    Optional<FamilyEntity> findFamilyWithProperties(@Param("id") long id);
}
