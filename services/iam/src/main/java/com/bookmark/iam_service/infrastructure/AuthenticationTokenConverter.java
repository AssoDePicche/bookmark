package com.bookmark.iam_service.infrastructure;

import com.bookmark.iam_service.application.IdentityService;
import com.bookmark.iam_service.domain.Identity;
import java.util.Collection;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;

public class AuthenticationTokenConverter implements Converter<Jwt, AbstractAuthenticationToken> {
  @Autowired private IdentityService service;

  @Override
  public AbstractAuthenticationToken convert(Jwt jwt) {
    String claim = jwt.getClaim("userId");

    UUID id = UUID.fromString(claim);

    Identity identity = service.query(id);

    UserDetails principal =
        User.builder()
            .accountExpired(false)
            .accountLocked(false)
            .credentialsExpired(false)
            .disabled(false)
            .username(identity.getUsername().toString())
            .password(identity.getPassword().toString())
            .authorities(identity.getRoles().stream().map(SimpleGrantedAuthority::new).toList())
            .build();

    Object credentials = null;

    Collection<? extends GrantedAuthority> authorities = principal.getAuthorities();

    return new UsernamePasswordAuthenticationToken(principal, credentials, authorities);
  }
}
