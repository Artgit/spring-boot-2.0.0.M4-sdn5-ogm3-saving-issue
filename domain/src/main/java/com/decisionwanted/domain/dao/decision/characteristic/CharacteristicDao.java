package com.decisionwanted.domain.dao.decision.characteristic;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import com.decisionwanted.domain.dao.BaseDao;
import com.decisionwanted.domain.model.decision.Decision;
import com.decisionwanted.domain.model.decision.characteristic.Characteristic;
import com.decisionwanted.domain.model.decision.characteristic.group.CharacteristicGroup;
import com.decisionwanted.domain.model.user.User;

@Validated
public interface CharacteristicDao extends BaseDao<Characteristic> {

	Characteristic create(@NotBlank String name, String description, boolean lazyOptions, boolean multiValue,
			@NotNull Decision ownerDecision, @NotNull User author, CharacteristicGroup characteristicGroup,
			Characteristic parentCharacteristic);

}