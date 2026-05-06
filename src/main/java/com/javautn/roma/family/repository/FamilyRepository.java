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
            inner join fetch f.province
            inner join fetch f.holding h
            inner join fetch h.property
            where f.id = :id
              and h.state = com.javautn.roma.holding.entity.HoldingState.ACTIVE
            """)
    Optional<FamilyEntity> findFamilyWithProperties(@Param("id") long id);

    @Query("""
            select distinct f
            from FamilyEntity f
            inner join fetch f.province
            left join fetch f.acquisitions a
            left join fetch a.slave
            where f.id = :id
              and a.state = com.javautn.roma.acquisition.entity.State.ACTIVE
            """)
    Optional<FamilyEntity> findFamilyWithSlaves(@Param("id") long id);

    @Query("""
            select distinct f
            from FamilyEntity f
            left join fetch f.assignations a
            left join fetch a.tax
            where f.id = :id
            """)
    Optional<FamilyEntity> findFamilyWithTaxAssignations(@Param("id") long id);

    @Query("""
            select distinct f
            from FamilyEntity f
            left join fetch f.province
            left join fetch f.familyRol fr
            left join fetch fr.citizen
            where f.id = :id
            """)
    Optional<FamilyEntity> findFamilyWithMembers(@Param("id") long id);
}
