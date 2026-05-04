package com.javautn.roma.human.repository;

import com.javautn.roma.human.entity.SlaveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SlaveRepository extends JpaRepository<SlaveEntity, Long> {
    @Query("""
        select distinct s
        from SlaveEntity s
        left join fetch s.acquisitions a
        left join fetch a.family f
        left join fetch f.province
        where s.id = :id
        """)
    Optional<SlaveEntity> findSlaveWithFamilies(@Param("id") long id);
}
