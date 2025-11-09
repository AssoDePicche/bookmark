package com.bookmark.iam.infrastructure;

import com.bookmark.iam.domain.Identity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationCoordinator {
  private final PasswordEncoder encoder;

  public AuthenticationCoordinator(PasswordEncoder encoder) {
    this.encoder = encoder;
  }

  public Identity verifyCredentials(String password, Identity identity) {
    if (!encoder.matches(password, identity.password())) {
      throw new SecurityException("Wrong name or Password");
    }

    return identity;
  }
}
