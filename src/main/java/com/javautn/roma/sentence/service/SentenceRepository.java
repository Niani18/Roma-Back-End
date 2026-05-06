package com.javautn.roma.sentence.service;

import com.javautn.roma.sentence.entity.SentenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

interface SentenceRepository extends JpaRepository<SentenceEntity, Long> {
}
