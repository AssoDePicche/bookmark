package com.bookmark.user_service.infrastructure;

import com.bookmark.user_service.application.IAMService;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

record AuthenticationRequest(String username, String password) {}

record RegistrationRequest(String username, String password, Set<String> roles) {}

@Service
public class IAMServiceImpl implements IAMService {
  private static final MediaType contentType = MediaType.APPLICATION_JSON;

  private final RestClient client;

  public IAMServiceImpl(RestClient.Builder builder, @Value("${client.iam.url}") String URL) {
    this.client = builder.baseUrl(URL).build();
  }

  @Override
  public AuthenticationResponse authenticate(String username, String password) {
    return client.post()
        .uri("/authenticate")
        .contentType(contentType)
        .body(new AuthenticationRequest(username, password))
        .retrieve()
        .body(AuthenticationResponse.class);
  }

  @Override
  public RegistrationResponse register(String username, String password, Set<String> roles) {
    return client.post()
        .uri("/register")
        .contentType(contentType)
        .body(new RegistrationRequest(username, password, roles))
        .retrieve()
        .body(RegistrationResponse.class);
  }

  @Override
  public IdentityResponse retrieveIdentity(String username, String token) {
    return client.get()
        .uri("/{username}", username)
        .header("Authorization", token)
        .retrieve()
        .body(IdentityResponse.class);
  }
}
