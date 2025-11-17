package com.bookmark.iam.domain;

import java.util.Set;
import java.util.UUID;

public class Identity {
  private UUID id;

  private Username username;

  private Password password;

  private Set<String> roles;

  public Identity(String username, String password, Set<String> roles) {
    this(UUID.randomUUID(), username, password, roles);
  }

  public Identity(UUID id, String username, String password, Set<String> roles) {
    this.id = id;

    this.username = new Username(username);

    this.password = new Password(password);

    this.roles = roles;
  }

  public UUID getId() {
    return id;
  }

  public Username getUsername() {
    return username;
  }

  public Password getPassword() {
    return password;
  }

  public Set<String> getRoles() {
    return roles;
  }
}
