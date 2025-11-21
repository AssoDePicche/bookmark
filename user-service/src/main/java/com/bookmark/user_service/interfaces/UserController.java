package com.bookmark.user_service.interfaces;

import com.bookmark.user_service.application.UserService;
import com.bookmark.user_service.domain.User;
import com.bookmark.user_service.domain.UserId;
import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  private final UserService service;

  public UserController(UserService service) {
    this.service = service;
  }

  @GetMapping("/me")
  public ResponseEntity<UserResponse> get(@RequestHeader("Authorization") String token,
      @RequestHeader("X-User-Username") String username) {
    if (null == username) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    try {
      User user = service.currentUser(username, token);

      UserResponse response = UserMapper.map(user);

      return ResponseEntity.ok(response);
    } catch (Exception exception) {
      throw new IllegalArgumentException(exception);
    }
  }

  @PostMapping("/register")
  public ResponseEntity<UserResponse> post(@RequestBody @Valid UserRegistrationRequest request)
      throws URISyntaxException {
    User user =
        service.create(request.email(), request.username(), request.password(), request.roles());

    String pathname = "/api/users/" + user.getId();

    var uri = new URI(pathname);

    UserResponse response = UserMapper.map(user);

    return ResponseEntity.created(uri).body(response);
  }
}
