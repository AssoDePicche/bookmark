package com.bookmark.infrastructure.security;

import com.bookmark.domain.User;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings({"serial"})
public class CustomUserDetails implements UserDetails {
  private final User user;

  public CustomUserDetails(User user) {
    this.user = user;
  }

  public Long getId() {
    return user.getId();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return user.getAuthorities();
  }

  @Override
  public String getPassword() {
    return user.getPassword().toString();
  }

  @Override
  public String getUsername() {
    return user.getUsername().toString();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
