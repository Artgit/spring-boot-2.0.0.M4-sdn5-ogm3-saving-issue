package com.decisionwanted.domain.repository.decision.characteristic.value;

import org.springframework.stereotype.Repository;

import com.decisionwanted.domain.model.decision.characteristic.value.Value;
import com.decisionwanted.domain.repository.BaseRepository;

@Repository
public interface ValueRepository extends BaseRepository<Value> {

	Value getById(Long valueId);

}