package com.javautn.roma.acquisition.repository;

import com.javautn.roma.acquisition.entity.AcquisitionEntity;
import com.javautn.roma.acquisition.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AcquisitionRepository extends JpaRepository<AcquisitionEntity, Long> {
    List<AcquisitionEntity> findByStateAndSlaveId(State state, long id);
    List<AcquisitionEntity> findBySlaveId(long id);
}
