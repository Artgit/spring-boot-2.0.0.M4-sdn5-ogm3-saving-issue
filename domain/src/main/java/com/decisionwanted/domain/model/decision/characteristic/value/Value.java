package com.decisionwanted.domain.model.decision.characteristic.value;

import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.decisionwanted.domain.model.BaseEntity;
import com.decisionwanted.domain.model.decision.Decision;
import com.decisionwanted.domain.model.user.User;

@NodeEntity
public class Value extends BaseEntity {

	private static final String SET_FOR = "SET_FOR";
	private static final String SET_ON = "SET_ON";

	@Index(unique = true)
	private Long id;

	@Relationship(type = SET_FOR, direction = Relationship.OUTGOING)
	private Decision decision;

	@Relationship(type = SET_ON, direction = Relationship.OUTGOING)
	private BaseEntity valuable;

	@Index(unique = false)
	private Object value;

	private String description;

	private Boolean available;

	private long totalHistoryValues;

	public Value() {
	}

	public Value(Object value, String description) {

		this.value = value;
		this.description = description;
	}

	public Value(Decision decision, BaseEntity valuable, User author, Object value, String description, Boolean available) {

		this(value, description);

		this.decision = decision;
		this.valuable = valuable;
		this.available = available;

		setCreateUser(author);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Decision getDecision() {
		return decision;
	}

	public void setDecision(Decision decision) {
		this.decision = decision;
	}

	public BaseEntity getValuable() {
		return valuable;
	}

	public void setValuable(BaseEntity valuable) {
		this.valuable = valuable;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getTotalHistoryValues() {
		return totalHistoryValues;
	}

	public void setTotalHistoryValues(long totalHistoryValues) {
		this.totalHistoryValues = totalHistoryValues;
	}
	
}