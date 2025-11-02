package com.bookmark.user.infrastructure;

import com.bookmark.user.application.UserService;
import com.bookmark.user.domain.User;
import com.bookmark.user.domain.UserId;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;

public class AuthenticationTokenConverter implements Converter<Jwt, AbstractAuthenticationToken> {
  @Autowired private UserService service;

  @Override
  public AbstractAuthenticationToken convert(Jwt jwt) {
    String userId = jwt.getClaim("userId");

    User user = service.query(new UserId(userId));

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
