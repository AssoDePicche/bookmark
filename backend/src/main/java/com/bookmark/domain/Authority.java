package com.bookmark.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
@SuppressWarnings({"serial"})
public class Authority implements GrantedAuthority {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

  @Column(unique = true) private String authority;

  @ManyToMany(fetch = FetchType.EAGER, mappedBy = "authorities") private List<User> users;

  public Authority() {}

  public Authority(String authority) {
    this.authority = authority;
  }

  @Override
  public String getAuthority() {
    return authority;
  }
}
