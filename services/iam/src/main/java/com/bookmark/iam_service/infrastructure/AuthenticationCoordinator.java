package com.bookmark.iam_service.infrastructure;

import com.bookmark.iam_service.domain.Identity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationCoordinator {
  private final PasswordEncoder encoder;

  public AuthenticationCoordinator(PasswordEncoder encoder) {
    this.encoder = encoder;
  }

  public String encode(String password) {
    return encoder.encode(password);
  }

  public Identity verifyCredentials(String password, Identity identity) {
    if (!encoder.matches(password, identity.getPassword().toString())) {
      throw new SecurityException("Wrong Username or Password");
    }

    return identity;
  }
}
