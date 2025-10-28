package com.bookmark.application;

import com.bookmark.domain.Password;
import com.bookmark.domain.User;
import com.bookmark.domain.Username;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

  public String authenticate(String username, String rawPassword) {
    return authenticate(new Username(username), new Password(rawPassword));
  }

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
                     .claim("userId", user.getId())
                     .build();

    var parameters = JwtEncoderParameters.from(claims);

    return encoder.encode(parameters).getTokenValue();
  }

  public AbstractAuthenticationToken authenticate(Jwt jwt) {
    Long userId = jwt.getClaim("userId");

    User user = service.query(userId);

    UserDetails principal =
        org.springframework.security.core.userdetails.User.builder()
            .accountExpired(false)
            .accountLocked(false)
            .credentialsExpired(false)
            .disabled(false)
            .username(user.getUsername().toString())
            .password(user.getPassword().toString())
            .authorities(List.of(user.getRole())
                             .stream()
                             .map(role -> new SimpleGrantedAuthority(role.toString()))
                             .toList())
            .build();

    Object credentials = null;

    Collection<? extends GrantedAuthority> authorities = principal.getAuthorities();

    return new UsernamePasswordAuthenticationToken(principal, credentials, authorities);
  }
}
