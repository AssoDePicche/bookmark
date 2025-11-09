package com.bookmark.user.infrastructure;

import com.bookmark.iam.domain.Identity;
import com.bookmark.iam.domain.IdentityProvider;
import com.bookmark.user.application.UserService;
import com.bookmark.user.domain.User;
import com.bookmark.user.domain.Username;
import org.springframework.stereotype.Service;

@Service
public class IdentityProviderAdapter implements IdentityProvider {
  private final UserService service;

  public IdentityProviderAdapter(UserService service) {
    this.service = service;
  }

  @Override
  public Identity findByUsername(String username) {
    User user = service.query(new Username(username));

    return new Identity(
        user.getId().toString(), user.getUsername().toString(), user.getPassword().toString());
  }
}
