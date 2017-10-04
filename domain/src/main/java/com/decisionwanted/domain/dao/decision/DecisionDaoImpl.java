package com.decisionwanted.domain.dao.decision;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.decisionwanted.domain.dao.BaseDaoImpl;
import com.decisionwanted.domain.dao.decision.characteristic.group.CharacteristicGroupDao;
import com.decisionwanted.domain.dao.decision.characteristic.group.CharacteristicGroupDaoImpl;
import com.decisionwanted.domain.model.decision.Decision;
import com.decisionwanted.domain.model.user.User;
import com.decisionwanted.domain.repository.BaseRepository;
import com.decisionwanted.domain.repository.decision.DecisionRepository;

@Service
@Transactional
public class DecisionDaoImpl extends BaseDaoImpl<Decision> implements DecisionDao {

	static final Logger logger = LoggerFactory.getLogger(DecisionDaoImpl.class);

	@Autowired
	private DecisionRepository decisionRepository;

	@Autowired
	private CharacteristicGroupDao characteristicGroupDao;

	@Override
	public Decision create(String name, String description, String url, boolean multiVotesAllowed,
			Decision parentDecision, User user) {

		Decision decision = createOrUpdate(
				new Decision(name, description, url, multiVotesAllowed, parentDecision, user), user);

		characteristicGroupDao.create(CharacteristicGroupDaoImpl.DEFAULT_CHARACTERISTIC_GROUP_NAME, null, decision,
				true, user);

		return decision;
	}

	@Override
	public Decision getById(Long decisionId) {
		return decisionRepository.getById(decisionId);
	}

	@Override
	@Transactional(readOnly = true)
	public BaseRepository<Decision> getRepository() {
		return decisionRepository;
	}

}