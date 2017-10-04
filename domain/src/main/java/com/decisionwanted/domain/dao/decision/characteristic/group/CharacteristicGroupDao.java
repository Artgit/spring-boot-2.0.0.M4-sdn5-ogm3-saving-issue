package com.decisionwanted.domain.dao.decision.characteristic.group;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import com.decisionwanted.domain.dao.BaseDao;
import com.decisionwanted.domain.model.decision.Decision;
import com.decisionwanted.domain.model.decision.characteristic.group.CharacteristicGroup;
import com.decisionwanted.domain.model.user.User;

@Validated
public interface CharacteristicGroupDao extends BaseDao<CharacteristicGroup> {

	CharacteristicGroup create(@NotBlank String name, String description, @NotNull Decision ownerDecision,
			boolean general, @NotNull User author);

}