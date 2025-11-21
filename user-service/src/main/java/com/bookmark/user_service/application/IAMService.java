package com.bookmark.user_service.application;

import java.util.Set;
import java.util.UUID;

public interface IAMService {
  static record AuthenticationResponse(String token) {}

  static record IdentityResponse(UUID id, String username, Set<String> roles) {}

  static record RegistrationResponse(UUID id, String username, Set<String> roles) {}

  AuthenticationResponse authenticate(String username, String password);

  RegistrationResponse register(String username, String password, Set<String> roles);

  IdentityResponse retrieveIdentity(String username, String token);
}
