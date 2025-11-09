package com.bookmark.user.interfaces;

import com.bookmark.user.application.UserService;
import com.bookmark.user.domain.Password;
import com.bookmark.user.domain.User;
import com.bookmark.user.domain.Username;
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
  public ResponseEntity<UserResponse> get(@AuthenticationPrincipal UserDetails details) {
    if (null == details) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    Username username = new Username(details.getUsername());

    User user = service.query(username);

    UserResponse response = UserMapper.map(user);

    return ResponseEntity.ok(response);
  }

  @PostMapping("/register")
  public ResponseEntity<UserResponse> post(@RequestBody @Valid UserRegistrationRequest request)
      throws URISyntaxException {
    User user = UserMapper.map(request);

    service.save(user);

    String pathname = "/api/users/" + user.getId();

    URI uri = new URI(pathname);

    UserResponse response = UserMapper.map(user);

    return ResponseEntity.created(uri).body(response);
  }
}
