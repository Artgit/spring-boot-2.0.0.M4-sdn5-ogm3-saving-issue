package com.decisionwanted.domain.model.decision.characteristic.group;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.decisionwanted.domain.model.BaseEntity;
import com.decisionwanted.domain.model.decision.Decision;
import com.decisionwanted.domain.model.decision.characteristic.Characteristic;
import com.decisionwanted.domain.model.user.User;

@NodeEntity
public class CharacteristicGroup extends BaseEntity {

	private final static String DEFINED_BY = "DEFINED_BY";
	private final static String CONTAINS = "CONTAINS";

	@Index(unique = true)
	private Long id;

	@Index(unique = false)
	private String name;

	@Index(unique = false)
	private String lowerName;

	private String description;

	@Relationship(type = DEFINED_BY, direction = Relationship.OUTGOING)
	private Decision ownerDecision;

	@Relationship(type = CONTAINS, direction = Relationship.OUTGOING)
	private Set<Characteristic> characteristics;

	private boolean general;

	public CharacteristicGroup() {
	}

	public CharacteristicGroup(String name, String description, Decision ownerDecision, User author) {
		setName(name);
		this.description = description;
		this.ownerDecision = ownerDecision;
		setCreateUser(author);
	}

	public CharacteristicGroup(String name, String description, Decision ownerDecision, boolean general, User author) {
		this(name, description, ownerDecision, author);
		this.general = general;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.lowerName = name.toLowerCase();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Decision getOwnerDecision() {
		return ownerDecision;
	}

	public void setOwnerDecision(Decision ownerDecision) {
		this.ownerDecision = ownerDecision;
	}

	public void addCharacteristic(Characteristic characteristic) {
		if (characteristics == null) {
			characteristics = new HashSet<>();
		}

		characteristics.add(characteristic);
		characteristic.setGroup(this);
	}

	public Set<Characteristic> getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristic(Set<Characteristic> characteristics) {
		this.characteristics = characteristics;
	}

	public boolean isGeneral() {
		return general;
	}

	public void setGeneral(boolean general) {
		this.general = general;
	}

}