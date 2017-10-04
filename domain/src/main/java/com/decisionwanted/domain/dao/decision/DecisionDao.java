package com.decisionwanted.domain.dao.decision;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.validation.annotation.Validated;

import com.decisionwanted.domain.dao.BaseDao;
import com.decisionwanted.domain.model.decision.Decision;
import com.decisionwanted.domain.model.user.User;

@Validated
public interface DecisionDao extends BaseDao<Decision> {
	
	Decision create(@NotBlank String name, String description, @URL String url, boolean multiVotesAllowed, Decision parentDecision, @NotNull User author);

}