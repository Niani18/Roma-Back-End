package com.javautn.roma.property.repository;

import com.javautn.roma.property.entity.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PropertyRepository extends JpaRepository<PropertyEntity, Long> {
    @Query("""
            select distinct p
            from PropertyEntity p
            left join fetch p.holdings h
            left join fetch h.family
            where h is null
               or h.state = com.javautn.roma.holding.entity.HoldingState.ACTIVE
            """)
    List<PropertyEntity> findAllPropertyWithOwners();

    @Query("""
            select distinct p
            from PropertyEntity p
            left join fetch p.holdings h
            left join fetch h.family
            where p.id = :id
              and (
                  h is null
                  or h.state = com.javautn.roma.holding.entity.HoldingState.ACTIVE
              )
            """)
    Optional<PropertyEntity> findPropertyWithOwners(@Param("id") long id);

    @Query("""
            select distinct p
            from PropertyEntity p
            left join fetch p.holdings h
            left join fetch h.family
            where p.id = :id
            """)
    Optional<PropertyEntity> findAllHoldingsWithOwners(@Param("id") long id);
}
