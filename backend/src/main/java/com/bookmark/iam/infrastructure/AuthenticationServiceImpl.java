package com.bookmark.iam.infrastructure;

import com.bookmark.iam.application.AuthenticationService;
import com.bookmark.iam.domain.Identity;
import com.bookmark.iam.domain.IdentityProvider;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
  private final AuthenticationCoordinator coordinator;

  private final IdentityProvider identityProvider;

  private final JsonWebTokenService service;

  public AuthenticationServiceImpl(AuthenticationCoordinator coordinator,
      IdentityProvider identityProvider, JsonWebTokenService service) {
    this.coordinator = coordinator;

    this.identityProvider = identityProvider;

    this.service = service;
  }

  @Override
  public String authenticate(String username, String password) {
    Identity identity = identityProvider.findByUsername(username);

    coordinator.verifyCredentials(password, identity);

    return service.generateToken(identity);
  }
}
