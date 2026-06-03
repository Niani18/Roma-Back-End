package com.javautn.roma.auth.repository;

import com.javautn.roma.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String email);

    Optional<UserEntity> findByCitizenId(long citizenId);

}
