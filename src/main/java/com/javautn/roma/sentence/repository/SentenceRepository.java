package com.javautn.roma.sentence.repository;

import com.javautn.roma.sentence.entity.SentenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SentenceRepository extends JpaRepository<SentenceEntity, Long> {
}
