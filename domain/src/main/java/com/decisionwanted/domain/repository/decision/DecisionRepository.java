package com.decisionwanted.domain.repository.decision;

import org.springframework.stereotype.Repository;

import com.decisionwanted.domain.model.decision.Decision;
import com.decisionwanted.domain.repository.BaseRepository;

@Repository
public interface DecisionRepository extends BaseRepository<Decision> {

	Decision getById(Long decisionId);
	
}