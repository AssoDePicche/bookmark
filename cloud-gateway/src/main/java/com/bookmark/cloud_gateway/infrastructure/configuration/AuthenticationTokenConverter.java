package com.bookmark.cloud_gateway.infrastructure.configuration;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

public class AuthenticationTokenConverter implements Converter<Jwt, AbstractAuthenticationToken> {
  @Override
  public AbstractAuthenticationToken convert(Jwt jwt) {
    String userId = jwt.getClaimAsString("userId");

    Collection<GrantedAuthority> authorities = jwt.getClaimAsStringList("roles")
                                                   .stream()
                                                   .map(SimpleGrantedAuthority::new)
                                                   .collect(Collectors.toList());

    return new UsernamePasswordAuthenticationToken(userId, null, authorities);
  }
}
