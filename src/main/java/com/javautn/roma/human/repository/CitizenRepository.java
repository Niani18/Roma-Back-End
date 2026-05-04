package com.javautn.roma.human.repository;

import com.javautn.roma.human.entity.CitizenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CitizenRepository extends JpaRepository<CitizenEntity, Long> {
    @Query("""
        select distinct c
        from CitizenEntity c
        left join fetch c.familyRol fr
        left join fetch fr.family f
        left join fetch f.province
        where c.id = :id
        """)
    Optional<CitizenEntity> findCitizenWithFamilies(@Param("id") long id);
}
