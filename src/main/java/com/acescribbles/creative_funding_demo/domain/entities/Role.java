package com.acescribbles.creative_funding_demo.domain.entities;

import com.acescribbles.creative_funding_demo.domain.enums.RoleType;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, unique = true)
	@Enumerated(EnumType.STRING)
	private RoleType name;

	@Column(nullable = false)
	private String description;

	@ManyToMany(mappedBy = "roles")
	private Set<User> users = new HashSet<>();

	@Column(nullable = false, updatable = false)
	private Instant createdAt;

	@Column
	private Instant lastModifiedAt;

	public Role(long id, RoleType name, String description, Set<User> users, Instant createdAt, Instant lastModifiedAt) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.users = users;
		this.createdAt = createdAt;
		this.lastModifiedAt = lastModifiedAt;
	}

	public Role() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public RoleType getName() {
		return name;
	}

	public void setName(RoleType name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
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
		Role role = (Role) o;
		return id == role.id && name == role.name && Objects.equals(description, role.description) && Objects.equals(createdAt, role.createdAt) && Objects.equals(lastModifiedAt, role.lastModifiedAt);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, description, createdAt, lastModifiedAt);
	}

	@PrePersist
	protected void onCreate() {
		Instant now = Instant.now();
		this.createdAt = now;
		this.lastModifiedAt = now;
	}

	@PreUpdate
	protected void onUpdate() {
		this.lastModifiedAt = Instant.now();
	}

}
