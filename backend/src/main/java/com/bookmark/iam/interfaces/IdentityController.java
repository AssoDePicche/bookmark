package com.bookmark.iam.interfaces;

import com.bookmark.common.domain.exception.ForbiddenException;
import com.bookmark.iam.application.IdentityService;
import com.bookmark.iam.domain.Identity;
import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/iam")
@RestController
@Validated
public class IdentityController {
  private final IdentityService service;

  public IdentityController(IdentityService service) {
    this.service = service;
  }

  @GetMapping("/{username}")
  public ResponseEntity<IdentityResponse> get(
      @AuthenticationPrincipal UserDetails details, @PathVariable String username) {
    if (!details.getUsername().equals(username)) {
      String message = "You Don't Have Permission To Access This Resource";

      throw new ForbiddenException(message);
    }

    Identity identity = service.query(username);

    var response = new IdentityResponse(
        identity.getId(), identity.getUsername().toString(), identity.getRoles());

    return ResponseEntity.ok(response);
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> post(
      @RequestBody @Valid AuthenticationRequest request) {
    String token = service.authenticate(request.username(), request.password());

    var response = new AuthenticationResponse(token);

    return ResponseEntity.ok(response);
  }

  @PostMapping("/register")
  public ResponseEntity<IdentityRegistrationResponse> post(
      @RequestBody @Valid IdentityRegistrationRequest request) throws URISyntaxException {
    Identity identity =
        service.createIdentity(request.username(), request.password(), request.roles());

    String pathname = "/api/iam/" + identity.getId();

    var uri = new URI(pathname);

    var response = new IdentityRegistrationResponse(
        identity.getId(), identity.getUsername().toString(), identity.getRoles());

    return ResponseEntity.created(uri).body(response);
  }
}
