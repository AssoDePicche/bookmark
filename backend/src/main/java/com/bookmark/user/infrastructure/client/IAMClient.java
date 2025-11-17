package com.bookmark.user.infrastructure.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;
import java.util.UUID;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class IAMClient {
  public static record AuthenticationRequest(String username, String password) {}

  public static record AuthenticationResponse(String token) {}

  public static record IdentityResponse(UUID id, String username, Set<String> roles) {}

  public static record RegistrationRequest(String username, String password, Set<String> roles) {}

  public static record RegistrationResponse(UUID id, String username, Set<String> roles) {}

  private static final MediaType contentType = MediaType.APPLICATION_JSON;

  private static final String URL = "http://localhost:8080/api/iam";

  private final RestClient client;

  public IAMClient(RestClient.Builder builder) {
    this.client = builder.baseUrl(URL).build();
  }

  public AuthenticationResponse authenticate(String username, String password) {
    return client.post()
        .uri("/authenticate")
        .contentType(contentType)
        .body(new AuthenticationRequest(username, password))
        .retrieve()
        .body(AuthenticationResponse.class);
  }

  public RegistrationResponse register(String username, String password, Set<String> roles) {
    return client.post()
        .uri("/register")
        .contentType(contentType)
        .body(new RegistrationRequest(username, password, roles))
        .retrieve()
        .body(RegistrationResponse.class);
  }

  public IdentityResponse retrieveIdentity(String username, String token) {
    return client.get()
        .uri("/{username}", username)
        .header("Authorization", token)
        .retrieve()
        .body(IdentityResponse.class);
  }
}
