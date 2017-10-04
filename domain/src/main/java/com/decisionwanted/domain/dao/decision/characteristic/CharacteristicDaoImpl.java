package com.decisionwanted.domain.dao.decision.characteristic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.decisionwanted.domain.dao.BaseDaoImpl;
import com.decisionwanted.domain.model.decision.Decision;
import com.decisionwanted.domain.model.decision.characteristic.Characteristic;
import com.decisionwanted.domain.model.decision.characteristic.group.CharacteristicGroup;
import com.decisionwanted.domain.model.user.User;
import com.decisionwanted.domain.repository.BaseRepository;
import com.decisionwanted.domain.repository.decision.characteristic.CharacteristicRepository;

@Service
@Transactional
public class CharacteristicDaoImpl extends BaseDaoImpl<Characteristic> implements CharacteristicDao {

	@Autowired
	private CharacteristicRepository characteristicRepository;

	@Override
	public Characteristic create(String name, String description, boolean lazyOptions, boolean multiValue,
			Decision ownerDecision, User author, CharacteristicGroup characteristicGroup,
			Characteristic parentCharacteristic) {

		return createOrUpdate(new Characteristic(name, description, lazyOptions, multiValue,
				ownerDecision, author, characteristicGroup, parentCharacteristic), author);

	}

	@Override
	@Transactional(readOnly = true)
	public BaseRepository<Characteristic> getRepository() {
		return characteristicRepository;
	}

}