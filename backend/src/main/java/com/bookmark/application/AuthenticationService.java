package com.bookmark.application;

import com.bookmark.domain.Password;
import com.bookmark.domain.User;
import com.bookmark.domain.Username;
import com.bookmark.infrastructure.security.CustomUserDetails;
import java.time.Instant;
import java.util.Collection;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
  private final JwtEncoder encoder;

  private final UserService service;

  public AuthenticationService(JwtEncoder encoder, UserService service) {
    this.encoder = encoder;

    this.service = service;
  }

  public String authenticate(Username username, Password rawPassword) {
    User user = service.query(username);

    if (!service.matches(rawPassword, user.getPassword())) {
      String message = String.format("Wrong Username or Password");

      throw new SecurityException(message);
    }

    var now = Instant.now();

    var secondsToExpire = 3600L;

    var expiresAt = now.plusSeconds(secondsToExpire);

    var subject = user.getUsername().toString();

    String issuer = "spring-security";

    var claims = JwtClaimsSet.builder()
                     .issuer(issuer)
                     .issuedAt(now)
                     .expiresAt(expiresAt)
                     .subject(subject)
                     .claim("userId", user.getId())
                     .build();

    var parameters = JwtEncoderParameters.from(claims);

    return encoder.encode(parameters).getTokenValue();
  }

  public AbstractAuthenticationToken authenticate(Jwt jwt) {
    Long userId = jwt.getClaim("userId");

    User user = service.query(userId);

    UserDetails principal = new CustomUserDetails(user);

    Object credentials = null;

    Collection<? extends GrantedAuthority> authorities = principal.getAuthorities();

    return new UsernamePasswordAuthenticationToken(principal, credentials, authorities);
  }
}
