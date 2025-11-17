package com.bookmark.iam.infrastructure.persistence;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "identity")
public class JpaIdentityEntity {
  @Id private UUID id;

  @Column(name = "username", nullable = false, unique = true) private String username;

  @Column(nullable = false) private String password;

  @CollectionTable(name = "identity_role", joinColumns = @JoinColumn(name = "identity_id"))
  @Column(name = "role_name")
  @ElementCollection(fetch = FetchType.EAGER)
  private Set<String> roles;

  public JpaIdentityEntity() {}

  public JpaIdentityEntity(UUID id, String username, String password, Set<String> roles) {
    this.id = id;

    this.username = username;

    this.password = password;

    this.roles = roles;
  }

  public UUID getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public Set<String> getRoles() {
    return roles;
  }
}
