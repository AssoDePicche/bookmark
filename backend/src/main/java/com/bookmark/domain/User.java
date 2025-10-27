package com.bookmark.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class User {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

  @AttributeOverride(name = "value", column = @Column(name = "username", unique = true))
  @Embedded
  private Username username;

  @AttributeOverride(name = "value", column = @Column(name = "email", unique = true))
  @Embedded
  private Email email;

  @AttributeOverride(name = "value", column = @Column(name = "password"))
  @Embedded
  private Password password;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_authority", joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "authority_id"))
  public List<Authority> authorities;

  @CreatedDate private LocalDateTime createdAt;

  @LastModifiedDate private LocalDateTime updatedAt;

  public User() {}

  public User(String username, String password) {
    this(null, username, password);
  }

  public User(String email, String username, String password) {
    this.email = new Email(email);

    this.username = new Username(username);

    this.password = new Password(password);
  }

  public Long getId() {
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

  public Collection<Authority> getAuthorities() {
    return authorities;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }
}
