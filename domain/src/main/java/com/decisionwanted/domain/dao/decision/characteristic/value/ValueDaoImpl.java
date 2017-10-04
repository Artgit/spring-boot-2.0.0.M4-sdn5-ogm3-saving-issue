package com.decisionwanted.domain.dao.decision.characteristic.value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.decisionwanted.domain.dao.BaseDaoImpl;
import com.decisionwanted.domain.dao.decision.characteristic.value.history.HistoryValueDao;
import com.decisionwanted.domain.model.BaseEntity;
import com.decisionwanted.domain.model.decision.Decision;
import com.decisionwanted.domain.model.decision.characteristic.value.Value;
import com.decisionwanted.domain.model.user.User;
import com.decisionwanted.domain.repository.BaseRepository;
import com.decisionwanted.domain.repository.decision.characteristic.value.ValueRepository;

@Service
@Transactional
public class ValueDaoImpl extends BaseDaoImpl<Value> implements ValueDao {

	@Autowired
	private ValueRepository valueRepository;

	@Autowired
	private HistoryValueDao historyValueDao;

	@Override
	public Value create(Decision decision, BaseEntity valuable, User author, Value value, Boolean available) {
		return create(decision, valuable, author, Arrays.asList(value), available).get(0);
	}

	@Override
	public List<Value> create(Decision decision, BaseEntity valuable, User author, List<Value> values,
			Boolean available) {

		List<Value> result = new ArrayList<>();

		for (Value value : values) {

			value = createOrUpdate(
					new Value(decision, valuable, author, value.getValue(), value.getDescription(), available), author);

			result.add(value);
		}

		return result;
	}

	@Override
	public Value update(Value value, Object newValue, String description, User author, Boolean available) {

		historyValueDao.create(value);

		value.setValue(newValue);
		value.setDescription(description);

		return createOrUpdate(value, author);
	}

	@Override
	public Value updateTotalHistoryValues(Value value, Integer increment) {

		long newTotalHistoryValues = value.getTotalHistoryValues() + increment;

		value.setTotalHistoryValues(newTotalHistoryValues);

		return createOrUpdate(value, false, null);
	}

	@Override
	@Transactional(readOnly = true)
	public Value getById(Long valueId) {
		return valueRepository.getById(valueId);
	}

	@Override
	@Transactional(readOnly = true)
	public BaseRepository<Value> getRepository() {
		return valueRepository;
	}

}