package com.bookmark.user.interfaces;

import com.bookmark.user.application.UserService;
import com.bookmark.user.domain.User;
import com.bookmark.user.domain.UserId;
import com.bookmark.user.infrastructure.client.IAMClient;
import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/users")
@RestController
@Validated
public class UserController {
  private final IAMClient client;

  private final UserService service;

  public UserController(IAMClient client, UserService service) {
    this.client = client;

    this.service = service;
  }

  @GetMapping("/me")
  public ResponseEntity<UserResponse> get(
      @AuthenticationPrincipal UserDetails details, @RequestHeader("Authorization") String token) {
    if (null == details) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    try {
      IAMClient.IdentityResponse identity = client.retrieveIdentity(details.getUsername(), token);

      UserId id = new UserId(identity.id());

      User user = service.query(id);

      UserResponse response = UserMapper.map(user);

      return ResponseEntity.ok(response);
    } catch (Exception exception) {
      throw new IllegalArgumentException(exception);
    }
  }

  @PostMapping("/register")
  public ResponseEntity<UserResponse> post(@RequestBody @Valid UserRegistrationRequest request)
      throws URISyntaxException {
    IAMClient.RegistrationResponse identity =
        client.register(request.username(), request.password(), request.roles());

    var id = identity.id().toString();

    var user = new User(id, request.email(), request.roles());

    service.save(user);

    String pathname = "/api/users/" + user.getId();

    var uri = new URI(pathname);

    UserResponse response = UserMapper.map(user);

    return ResponseEntity.created(uri).body(response);
  }
}
