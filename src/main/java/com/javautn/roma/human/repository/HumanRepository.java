package com.javautn.roma.human.repository;

import com.javautn.roma.human.entity.HumanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HumanRepository extends JpaRepository<HumanEntity, Long> {
}
