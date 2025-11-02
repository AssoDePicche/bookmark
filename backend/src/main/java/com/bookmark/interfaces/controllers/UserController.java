package com.bookmark.interfaces.controllers;

import com.bookmark.application.UserService;
import com.bookmark.domain.User;
import com.bookmark.domain.Username;
import com.bookmark.infrastructure.adapters.application.AuthenticationServiceAdapter;
import com.bookmark.interfaces.dto.user.UserAuthenticationRequest;
import com.bookmark.interfaces.dto.user.UserAuthenticationResponse;
import com.bookmark.interfaces.dto.user.UserRegistrationRequest;
import com.bookmark.interfaces.dto.user.UserResponse;
import com.bookmark.interfaces.mappers.UserMapper;
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
  private final UserService userService;

  private final AuthenticationServiceAdapter authenticationService;

  public UserController(
      UserService userService, AuthenticationServiceAdapter authenticationService) {
    this.userService = userService;

    this.authenticationService = authenticationService;
  }

  @GetMapping("/me")
  public ResponseEntity<UserResponse> get(@AuthenticationPrincipal UserDetails details) {
    if (null == details) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    Username username = new Username(details.getUsername());

    User user = userService.query(username);

    UserResponse response = UserMapper.map(user);

    return ResponseEntity.ok(response);
  }

  @PostMapping("/register")
  public ResponseEntity<UserResponse> post(@RequestBody @Valid UserRegistrationRequest request)
      throws URISyntaxException {
    User user = UserMapper.map(request);

    userService.save(user);

    String pathname = "/api/users/" + user.getId();

    URI uri = new URI(pathname);

    UserResponse response = UserMapper.map(user);

    return ResponseEntity.created(uri).body(response);
  }

  @PostMapping("/authenticate")
  public ResponseEntity<UserAuthenticationResponse> post(
      @RequestBody @Valid UserAuthenticationRequest request) {
    String token = authenticationService.authenticate(request.username(), request.password());

    UserAuthenticationResponse response = new UserAuthenticationResponse(token);

    return ResponseEntity.ok(response);
  }
}
