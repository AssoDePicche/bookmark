package com.bookmark.infrastructure.security;

import com.bookmark.infrastructure.adapters.application.AuthenticationServiceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;

public class AuthenticationTokenConverter implements Converter<Jwt, AbstractAuthenticationToken> {
  @Autowired private AuthenticationServiceAdapter service;

  @Override
  public AbstractAuthenticationToken convert(Jwt jwt) {
    return service.authenticate(jwt);
  }
}
