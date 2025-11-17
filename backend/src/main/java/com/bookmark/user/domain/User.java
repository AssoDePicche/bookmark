package com.bookmark.user.domain;

import java.util.Set;
import java.util.stream.Collectors;

public class User {
  private UserId id;

  private Email email;

  private Set<UserRole> roles;

  public User(String email, Set<String> roles) {
    this(new UserId(), email, roles);
  }

  public User(String id, String email, Set<String> roles) {
    this(new UserId(id), email, roles);
  }

  public User(UserId id, String email, Set<String> roles) {
    this.id = id;

    this.email = new Email(email);

    this.roles = roles.stream().map(UserRole::from).collect(Collectors.toSet());
  }

  public UserId getId() {
    return id;
  }

  public Email getEmail() {
    return email;
  }

  public Set<UserRole> getRoles() {
    return roles;
  }
}
