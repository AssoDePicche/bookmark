package com.bookmark.user.infrastructure.persistence;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user")
public class JpaUserEntity {
  @Id private UUID id;

  @Column(name = "email", nullable = false, unique = true) private String email;

  @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
  @Column(name = "role_name")
  @ElementCollection(fetch = FetchType.EAGER)
  public Set<String> roles;

  public JpaUserEntity() {}

  public JpaUserEntity(UUID id, String email, Set<String> roles) {
    this.id = id;

    this.email = email;

    this.roles = roles;
  }

  public UUID getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public Set<String> getRoles() {
    return roles;
  }
}
