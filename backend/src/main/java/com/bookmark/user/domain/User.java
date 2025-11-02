package com.bookmark.user.domain;

public class User {
  private UserId id;

  private Username username;

  private Email email;

  private Password password;

  private UserRole role;

  public User(String email, String username, String password, String role) {
    this(new UserId(), email, username, password, role);
  }

  public User(UserId id, String email, String username, String password, String role) {
    this.id = id;

    this.email = new Email(email);

    this.username = new Username(username);

    this.password = new Password(password);

    this.role = UserRole.from(role);
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
}
