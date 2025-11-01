package com.bookmark.infrastructure.persistence;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user")
public class JpaUserEntity {
  @Id private UUID id;

  @Column(name = "username", unique = true) private String username;

  @Column(name = "email", unique = true) private String email;

  @Column(name = "password") private String password;

  public String role;

  @Column(name = "created_at", nullable = false, updatable = false)
  @CreatedDate
  private LocalDateTime createdAt;

  @Column(name = "updated_at", nullable = false) @LastModifiedDate private LocalDateTime updatedAt;

  public JpaUserEntity() {}

  public JpaUserEntity(UUID id, String username, String email, String password, String role) {
    this.id = id;

    this.username = username;

    this.email = email;

    this.password = password;

    this.role = role;
  }

  public UUID getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getRole() {
    return role;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }
}
