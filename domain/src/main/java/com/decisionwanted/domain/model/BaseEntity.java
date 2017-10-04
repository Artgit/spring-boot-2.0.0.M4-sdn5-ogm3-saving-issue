package com.decisionwanted.domain.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.decisionwanted.domain.model.user.User;

@NodeEntity
public abstract class BaseEntity {

	private static final String CREATED_BY = "CREATED_BY";
	private static final String UPDATED_BY = "UPDATED_BY";
	private static final String OWNED_BY = "OWNED_BY";

	@GraphId
	private Long graphId;

	@Index(unique = false)
	private Date createDate;

	@Relationship(type = CREATED_BY, direction = Relationship.OUTGOING)
	private User createUser;

	@Index(unique = false)
	private Date updateDate;

	@Relationship(type = UPDATED_BY, direction = Relationship.OUTGOING)
	private User updateUser;

	@Relationship(type = OWNED_BY, direction = Relationship.OUTGOING)
	private Set<User> ownerUsers;

	public BaseEntity() {
	}

	public Long getGraphId() {
		return graphId;
	}

	public void setGraphId(Long graphId) {
		this.graphId = graphId;
	}

	public abstract Long getId();

	public abstract void setId(Long id);

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return returns the user by whom this was created.
	 */
	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return returns the user by whom this was updated.
	 */
	public User getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}

	public Set<User> getOwnerUsers() {
		return ownerUsers;
	}

	public void setOwnerUsers(Set<User> ownerUsers) {
		this.ownerUsers = ownerUsers;
	}

	public boolean addOwnerUser(User ownerUser) {

		if (ownerUsers == null) {
			ownerUsers = new HashSet<>();
		}

		return ownerUsers.add(ownerUser);
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;

		if (!(o instanceof BaseEntity))
			return false;

		if (o.getClass() != getClass())
			return false;

		BaseEntity that = (BaseEntity) o;

		if (getId() != null) {
			if (!getId().equals(that.getId()))
				return false;
		} else {
			if (that.getId() != null)
				return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return 31 + ((getId() == null) ? 0 : getId().hashCode());
	}

	@Override
	public String toString() {
		return "BaseEntity [id=" + getId() + ", class=" + getClass() + ", createDate=" + createDate + ", updateDate="
				+ updateDate + "]";
	}

}