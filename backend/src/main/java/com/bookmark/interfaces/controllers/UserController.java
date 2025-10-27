package com.bookmark.interfaces.controllers;

import com.bookmark.application.AuthenticationService;
import com.bookmark.application.UserService;
import com.bookmark.domain.User;
import com.bookmark.domain.Username;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/users")
@RestController
public class UserController {
  private final UserMapper mapper;

  private final UserService service;

  private final AuthenticationService authenticationService;

  public UserController(
      UserMapper mapper, UserService service, AuthenticationService authenticationService) {
    this.mapper = mapper;

    this.service = service;

    this.authenticationService = authenticationService;
  }

  @GetMapping("/me")
  public ResponseEntity<UserResponse> get(@AuthenticationPrincipal UserDetails details) {
    if (null == details) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    Username username = new Username(details.getUsername());

    User user = service.query(username);

    UserResponse response = mapper.map(user);

    return ResponseEntity.ok(response);
  }

  @PostMapping("/register")
  public ResponseEntity<UserResponse> post(@RequestBody @Valid UserRegistrationRequest request)
      throws URISyntaxException {
    User user = mapper.map(request);

    service.save(user);

    String pathname = String.format("/api/users/%d", user.getId());

    URI uri = new URI(pathname);

    UserResponse response = mapper.map(user);

    return ResponseEntity.created(uri).body(response);
  }

  @PostMapping("/authenticate")
  public ResponseEntity<UserAuthenticationResponse> post(
      @RequestBody @Valid UserAuthenticationRequest request) {
    User user = mapper.map(request);

    String token = authenticationService.authenticate(user.getUsername(), user.getPassword());

    UserAuthenticationResponse response = new UserAuthenticationResponse(token);

    return ResponseEntity.ok(response);
  }
}
