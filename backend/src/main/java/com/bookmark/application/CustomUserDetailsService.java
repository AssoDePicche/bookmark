package com.bookmark.application;

import com.bookmark.domain.User;
import com.bookmark.infrastructure.persistence.JpaUserRepository;
import com.bookmark.infrastructure.security.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  private final JpaUserRepository repository;

  public CustomUserDetailsService(JpaUserRepository repository) {
    this.repository = repository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = repository.findByUsername_Value(username).orElseThrow(() -> {
      var message = String.format("User Not Found With Username '%s'", username);

      throw new UsernameNotFoundException(message);
    });

    return new CustomUserDetails(user);
  }
}
