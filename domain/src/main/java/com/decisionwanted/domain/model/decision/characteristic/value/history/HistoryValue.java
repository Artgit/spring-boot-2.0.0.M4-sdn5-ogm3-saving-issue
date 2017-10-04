package com.decisionwanted.domain.model.decision.characteristic.value.history;

import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.decisionwanted.domain.model.BaseEntity;
import com.decisionwanted.domain.model.decision.characteristic.value.Value;
import com.decisionwanted.domain.model.user.User;

@NodeEntity
public class HistoryValue extends BaseEntity {

	private static final String CONTAINS = "CONTAINS";

	@Index(unique = true)
	private Long id;

	@Index(unique = false)
	private Object originalValue;

	private String description;

	@Relationship(type = CONTAINS, direction = Relationship.INCOMING)
	private Value value;

	public HistoryValue() {
	}

	public HistoryValue(Value value) {
		this.value = value;
		this.value.setTotalHistoryValues(this.value.getTotalHistoryValues() + 1);
		this.originalValue = value.getValue();
		this.description = value.getDescription();
		User author = value.getUpdateUser() != null ? value.getUpdateUser() : value.getCreateUser();
		setCreateDate(value.getUpdateDate() != null ? value.getUpdateDate() : value.getCreateDate());
		setCreateUser(author);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Object getOriginalValue() {
		return originalValue;
	}

	public void setOriginalValue(Object originalValue) {
		this.originalValue = originalValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}

}