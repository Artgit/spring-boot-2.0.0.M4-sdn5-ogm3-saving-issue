package com.decisionwanted.domain.dao.decision.characteristic.value.history;

import static java.util.Optional.ofNullable;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.decisionwanted.domain.dao.BaseDaoImpl;
import com.decisionwanted.domain.dao.decision.characteristic.value.ValueDao;
import com.decisionwanted.domain.model.decision.characteristic.value.Value;
import com.decisionwanted.domain.model.decision.characteristic.value.history.HistoryValue;
import com.decisionwanted.domain.repository.BaseRepository;
import com.decisionwanted.domain.repository.decision.characteristic.value.history.HistoryValueRepository;

@Service
@Transactional
public class HistoryValueDaoImpl extends BaseDaoImpl<HistoryValue> implements HistoryValueDao {

	@Autowired
	private HistoryValueRepository historyValueRepository;

	@Lazy
	@Autowired
	private ValueDao valueDao;

	@Override
	public HistoryValue create(Value value) {

		ofNullable(value.getValue()).orElseThrow(() -> new IllegalArgumentException("Value value can't be null"));
		ofNullable(value.getCreateUser()).orElseThrow(() -> new IllegalArgumentException("Value create user can't be null"));
		ofNullable(value.getCreateDate()).orElseThrow(() -> new IllegalArgumentException("Value create date can't be null"));

		HistoryValue historyValue = new HistoryValue(value);

		//TODO: why do I need to use save depth:2 here ????????????
		return createOrUpdate(historyValue, false, 1, null);
	}

	@Override
	@Transactional(readOnly = true)
	public HistoryValue getById(Long historyValueId) {
		return historyValueRepository.getById(historyValueId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<HistoryValue> findAllByValueId(Long valueId) {
		return historyValueRepository.findAllByValueId(valueId);
	}

	@Override
	@Transactional(readOnly = true)
	public BaseRepository<HistoryValue> getRepository() {
		return historyValueRepository;
	}

}