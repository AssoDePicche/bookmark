package com.bookmark.user.infrastructure;

import com.bookmark.user.application.AuthenticationService;
import com.bookmark.user.application.UserService;
import com.bookmark.user.domain.Password;
import com.bookmark.user.domain.User;
import com.bookmark.user.domain.UserId;
import com.bookmark.user.domain.Username;
import java.time.Instant;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
  private final JwtEncoder encoder;

  private final UserService service;

  public AuthenticationServiceImpl(JwtEncoder encoder, UserService service) {
    this.encoder = encoder;

    this.service = service;
  }

  @Override
  public String authenticate(Username username, Password rawPassword) {
    User user = service.query(username);

    if (!service.matches(rawPassword, user.getPassword())) {
      String message = String.format("Wrong Username or Password");

      throw new SecurityException(message);
    }

    var claims = JwtClaimsSet.builder()
                     .issuer("spring-security")
                     .issuedAt(Instant.now())
                     .expiresAt(Instant.now().plusSeconds(3600L))
                     .subject(user.getUsername().toString())
                     .claim("userId", user.getId().toString())
                     .build();

    var parameters = JwtEncoderParameters.from(claims);

    return encoder.encode(parameters).getTokenValue();
  }
}
