package com.acescribbles.creative_funding_demo.domain.entities;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String passwordHash;

	@Column(nullable = false, length = 50)
	private String displayName;

	@Column(nullable = false, unique = true, length = 30)
	private String handle;

	@ManyToMany
	@JoinTable(
			name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Role> roles = new HashSet<>();

	@Column(nullable = false, updatable = false)
	private Instant createdAt;

	@Column
	private Instant lastModifiedAt;

	public User(long id, String email, String passwordHash, String displayName, String handle, Set<Role> roles, Instant createdAt, Instant lastModifiedAt) {
		this.id = id;
		this.email = email;
		this.passwordHash = passwordHash;
		this.displayName = displayName;
		this.handle = handle;
		this.roles = roles;
		this.createdAt = createdAt;
		this.lastModifiedAt = lastModifiedAt;
	}

	public User() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getLastUpdatedAt() {
		return lastModifiedAt;
	}

	public void setLastUpdatedAt(Instant lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return id == user.id && Objects.equals(email, user.email) && Objects.equals(passwordHash, user.passwordHash) && Objects.equals(displayName, user.displayName) && Objects.equals(handle, user.handle) && Objects.equals(createdAt, user.createdAt) && Objects.equals(lastModifiedAt, user.lastModifiedAt);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, email, passwordHash, displayName, handle, createdAt, lastModifiedAt);
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = Instant.now();
	}

	@PreUpdate
	protected void onUpdate() {
		this.lastModifiedAt = Instant.now();
	}
}
