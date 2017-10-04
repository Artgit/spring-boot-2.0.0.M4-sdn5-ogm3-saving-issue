package com.decisionwanted.domain.model.decision.characteristic;

import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.decisionwanted.domain.model.BaseEntity;
import com.decisionwanted.domain.model.decision.Decision;
import com.decisionwanted.domain.model.decision.characteristic.group.CharacteristicGroup;
import com.decisionwanted.domain.model.user.User;

@NodeEntity
public class Characteristic extends BaseEntity {

	private static final String CONTAINS = "CONTAINS";
	private static final String DEFINED_BY = "DEFINED_BY";

	@Index(unique = true)
	private Long id;

	@Index(unique = false)
	private String name;

	@Index(unique = false)
	private String lowerName;

	private String description;

	private boolean lazyOptions;

	@Relationship(type = CONTAINS, direction = Relationship.INCOMING)
	private CharacteristicGroup group;

	@Relationship(type = DEFINED_BY, direction = Relationship.OUTGOING)
	private Decision ownerDecision;

	@Relationship(type = CONTAINS, direction = Relationship.INCOMING)
	private Characteristic parentCharacteristic;

	private int sortWeight;

	public Characteristic() {
	}

	public Characteristic(String name, String description, boolean lazyOptions, boolean multiValue,Decision ownerDecision, User author) {

		setName(name);
		this.description = description;
		this.lazyOptions = lazyOptions;

		this.ownerDecision = ownerDecision;

		setCreateUser(author);
	}

	public Characteristic(String name, String description, boolean lazyOptions, boolean multiValue, Decision ownerDecision, User author, CharacteristicGroup group) {

		this(name, description, lazyOptions, multiValue, ownerDecision, author);

		if (group != null) {
			this.group = group;
			this.group.addCharacteristic(this);
		}
	}

	public Characteristic(String name, String description, boolean lazyOptions, boolean multiValue, Decision ownerDecision, User author, CharacteristicGroup group,
			Characteristic parentCharacteristic) {

		this(name, description, lazyOptions, multiValue, ownerDecision, author, group);

		this.parentCharacteristic = parentCharacteristic;
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

	public boolean isLazyOptions() {
		return lazyOptions;
	}

	public void setLazyOptions(boolean lazyOptions) {
		this.lazyOptions = lazyOptions;
	}

	public String getLowerName() {
		return lowerName;
	}

	public void setLowerName(String lowerName) {
		this.lowerName = lowerName;
	}

	public CharacteristicGroup getGroup() {
		return group;
	}

	public void setGroup(CharacteristicGroup group) {
		this.group = group;
	}

	public Decision getOwnerDecision() {
		return ownerDecision;
	}

	public void setOwnerDecision(Decision ownerDecision) {
		this.ownerDecision = ownerDecision;
	}

	public Characteristic getParentCharacteristic() {
		return parentCharacteristic;
	}

	public void setParentCharacteristic(Characteristic parentCharacteristic) {
		this.parentCharacteristic = parentCharacteristic;
	}

	public int getSortWeight() {
		return sortWeight;
	}

	public void setSortWeight(int sortWeight) {
		this.sortWeight = sortWeight;
	}

}