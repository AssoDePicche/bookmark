package com.bookmark.iam.interfaces;

import com.bookmark.iam.application.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/iam")
@RestController
@Validated
public class AuthenticationController {
  private final AuthenticationService service;

  public AuthenticationController(AuthenticationService service) {
    this.service = service;
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> post(
      @RequestBody @Valid AuthenticationRequest request) {
    String token = service.authenticate(request.username(), request.password());

    AuthenticationResponse response = new AuthenticationResponse(token);

    return ResponseEntity.ok(response);
  }
}
