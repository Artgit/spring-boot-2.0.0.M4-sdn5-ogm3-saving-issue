package com.decisionwanted.domain.model.decision;

import java.util.HashSet;
import java.util.Set;

import javax.print.attribute.standard.Media;

import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.decisionwanted.domain.model.BaseEntity;
import com.decisionwanted.domain.model.user.User;

@NodeEntity
public class Decision extends BaseEntity {

	private static final String DEFINED_BY = "DEFINED_BY";
	private static final String CONTAINS = "CONTAINS";
	public static final String FOLLOWS = "FOLLOWS";

	@Index(unique = true)
	private Long id;

	@Index(unique = false)
	private String name;

	@Index(unique = false)
	private String lowerName;

	private String description;

	private String url;

	private String email;

	private String logoUrl;

	private String imageUrl;

	@Relationship(type = CONTAINS, direction = Relationship.INCOMING)
	private Set<Decision> parentDecisions;

	@Index(unique = false)
	private int totalChildDecisions;

	@Relationship(type = DEFINED_BY, direction = Relationship.INCOMING)
	private Set<Media> medias;

	@Index(unique = false)
	private int totalViews;

	private boolean multiVotesAllowed;

	public Decision() {
	}

	public Decision(String name, String description, String url, boolean multiVotesAllowed, Decision parentDecision,
			User author) {

		setName(name);
		this.description = description;
		this.url = url;

		if (parentDecision != null) {
			addParentDecision(parentDecision);
			parentDecision.incrementTotalChildDecisions();
		}

		setCreateUser(author);

		setMultiVotesAllowed(multiVotesAllowed);
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Set<Decision> getParentDecisions() {
		return parentDecisions;
	}

	public void setParentDecisions(Set<Decision> parentDecisions) {
		this.parentDecisions = parentDecisions;
	}

	public boolean addParentDecision(Decision parentDecision) {

		if (parentDecisions == null) {
			parentDecisions = new HashSet<>();
		}

		return parentDecisions.add(parentDecision);
	}

	public int getTotalChildDecisions() {
		return totalChildDecisions;
	}

	public void setTotalChildDecisions(int totalChildDecisions) {
		this.totalChildDecisions = totalChildDecisions;
	}

	public void incrementTotalChildDecisions() {
		totalChildDecisions++;
	}

	public Set<Media> getMedias() {
		return medias;
	}

	public void setMedias(Set<Media> medias) {
		this.medias = medias;
	}

	public boolean addMedia(Media media) {

		if (medias == null) {
			medias = new HashSet<>();
		}

		return medias.add(media);
	}

	public int getTotalViews() {
		return totalViews;
	}

	public void setTotalViews(int totalViews) {
		this.totalViews = totalViews;
	}

	public void incrementTotalViews() {
		totalViews++;
	}

	public boolean isMultiVotesAllowed() {
		return multiVotesAllowed;
	}

	public void setMultiVotesAllowed(boolean multiVotesAllowed) {
		this.multiVotesAllowed = multiVotesAllowed;
	}

}