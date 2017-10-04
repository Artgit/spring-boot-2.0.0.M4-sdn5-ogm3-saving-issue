package com.decisionwanted.domain.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import com.decisionwanted.domain.model.BaseEntity;

@Repository
public interface BaseRepository<T extends BaseEntity> extends Neo4jRepository<T, Long> {
}
