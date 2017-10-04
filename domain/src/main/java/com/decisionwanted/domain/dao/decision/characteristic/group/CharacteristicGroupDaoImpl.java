package com.decisionwanted.domain.dao.decision.characteristic.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.decisionwanted.domain.dao.BaseDaoImpl;
import com.decisionwanted.domain.model.decision.Decision;
import com.decisionwanted.domain.model.decision.characteristic.group.CharacteristicGroup;
import com.decisionwanted.domain.model.user.User;
import com.decisionwanted.domain.repository.BaseRepository;
import com.decisionwanted.domain.repository.decision.characteristic.group.CharacteristicGroupRepository;

@Service
@Transactional
public class CharacteristicGroupDaoImpl extends BaseDaoImpl<CharacteristicGroup> implements CharacteristicGroupDao {

	public static final String DEFAULT_CHARACTERISTIC_GROUP_NAME = "General";

	@Autowired
	private CharacteristicGroupRepository characteristicGroupRepository;

	@Override
	public CharacteristicGroup create(String name, String description, Decision ownerDecision, boolean general,
			User author) {
		return createOrUpdate(new CharacteristicGroup(name, description, ownerDecision, general, author), author);
	}

	@Override
	@Transactional(readOnly = true)
	public BaseRepository<CharacteristicGroup> getRepository() {
		return characteristicGroupRepository;
	}
}