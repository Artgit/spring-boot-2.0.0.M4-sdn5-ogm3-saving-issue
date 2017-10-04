package com.decisionwanted.domain.repository.decision.characteristic.group;

import org.springframework.stereotype.Repository;

import com.decisionwanted.domain.model.decision.characteristic.group.CharacteristicGroup;
import com.decisionwanted.domain.repository.BaseRepository;

@Repository
public interface CharacteristicGroupRepository extends BaseRepository<CharacteristicGroup> {

	CharacteristicGroup getById(Long characteristicGroupId);

}