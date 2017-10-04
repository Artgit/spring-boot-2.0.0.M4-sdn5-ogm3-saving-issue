package com.decisionwanted.domain.model.user;

import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.decisionwanted.domain.model.BaseEntity;

@NodeEntity
public class User extends BaseEntity {

	private static final String REFERRED_BY = "REFERRED_BY";

	@Index(unique = true)
	private Long id;

	@Relationship(type = REFERRED_BY, direction = Relationship.OUTGOING)
	private User referrer;

	@Index(unique = true)
	private String username;

	private boolean usernameConfirmed = false;

	private String password;

	@Index(unique = true)
	private String email;

	private boolean emailConfirmed = false;

	private String firstName;

	private String lastName;

	private String about;

	private String url;

	private boolean accountNonExpired = true;

	private boolean accountNonLocked = true;

	private boolean credentialsNonExpired = true;

	private boolean enabled = true;

	private long reputation;

	public User() {
	}

	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public User(String username, String password, String email, String firstName, String lastName) {
		this(username, password, email);
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getReferrer() {
		return referrer;
	}

	public void setReferrer(User referrer) {
		this.referrer = referrer;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isUsernameConfirmed() {
		return usernameConfirmed;
	}

	public void setUsernameConfirmed(boolean usernameConfirmed) {
		this.usernameConfirmed = usernameConfirmed;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEmailConfirmed() {
		return emailConfirmed;
	}

	public void setEmailConfirmed(boolean emailConfirmed) {
		this.emailConfirmed = emailConfirmed;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Indicates whether the user's account has expired. An expired account
	 * cannot be authenticated.
	 *
	 * @return <code>true</code> if the user's account is valid (ie
	 *         non-expired), <code>false</code> if no longer valid (ie expired)
	 */
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	/**
	 * Indicates whether the user is locked or unlocked. A locked user cannot be
	 * authenticated.
	 *
	 * @return <code>true</code> if the user is not locked, <code>false</code>
	 *         otherwise
	 */
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	/**
	 * Indicates whether the user's credentials (password) has expired. Expired
	 * credentials prevent authentication.
	 *
	 * @return <code>true</code> if the user's credentials are valid (ie
	 *         non-expired), <code>false</code> if no longer valid (ie expired)
	 */
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	/**
	 * Indicates whether the user is enabled or disabled. A disabled user cannot
	 * be authenticated.
	 *
	 * @return <code>true</code> if the user is enabled, <code>false</code>
	 *         otherwise
	 */
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public long getReputation() {
		return reputation;
	}

	public void setReputation(long reputation) {
		this.reputation = reputation;
	}

	public void incrementReputation(long reputation) {
		this.reputation += reputation;
	}

}