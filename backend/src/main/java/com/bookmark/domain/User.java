package com.bookmark.domain;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public class User {
  private UserId id;

  private Username username;

  private Email email;

  private Password password;

  private UserRole role;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  public User(String email, String username, String password, String role) {
    this(new UserId(), email, username, password, role, null, null);
  }

  public User(UserId id, String email, String username, String password, String role,
      LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;

    this.email = new Email(email);

    this.username = new Username(username);

    this.password = new Password(password);

    this.role = UserRole.from(role);

    this.createdAt = createdAt;

    this.updatedAt = updatedAt;
  }

  public UserId getId() {
    return id;
  }

  public Username getUsername() {
    return username;
  }

  public Email getEmail() {
    return email;
  }

  public Password getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = new Password(password);
  }

  public UserRole getRole() {
    return role;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }
}
