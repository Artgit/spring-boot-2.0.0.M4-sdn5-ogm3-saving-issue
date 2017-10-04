package com.decisionwanted.domain.dao.decision.characteristic.value;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.decisionwanted.domain.dao.BaseDao;
import com.decisionwanted.domain.model.BaseEntity;
import com.decisionwanted.domain.model.decision.Decision;
import com.decisionwanted.domain.model.decision.characteristic.value.Value;
import com.decisionwanted.domain.model.user.User;

@Validated
public interface ValueDao extends BaseDao<Value> {

	List<Value> create(@NotNull Decision decision, @NotNull BaseEntity valuable, @NotNull User author,
			@NotNull List<Value> values, Boolean available);

	Value create(@NotNull Decision decision, @NotNull BaseEntity valuable, @NotNull User author, @NotNull Value value,
			Boolean available);

	Value update(@NotNull Value value, @NotNull Object newValue, String description, @NotNull User author,
			Boolean available);

	Value updateTotalHistoryValues(@NotNull Value value, @NotNull Integer increment);

}