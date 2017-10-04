package com.decisionwanted.domain.dao.decision.characteristic.value.history;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.decisionwanted.domain.dao.BaseDao;
import com.decisionwanted.domain.model.decision.characteristic.value.Value;
import com.decisionwanted.domain.model.decision.characteristic.value.history.HistoryValue;

@Validated
public interface HistoryValueDao extends BaseDao<HistoryValue> {

	HistoryValue create(@NotNull Value value);

	List<HistoryValue> findAllByValueId(Long valueId);

}