package com.javautn.roma.holding.repository;

import com.javautn.roma.holding.entity.HoldingEntity;
import com.javautn.roma.holding.entity.HoldingState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HoldingRepository extends JpaRepository<HoldingEntity, Long> {
    List<HoldingEntity> findByPropertyIdAndState(long propertyId, HoldingState state);
}
