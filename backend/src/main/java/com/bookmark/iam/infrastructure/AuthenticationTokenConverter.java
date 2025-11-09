package com.bookmark.iam.infrastructure;

import com.bookmark.iam.domain.Details;
import com.bookmark.iam.domain.DetailsProvider;
import java.util.Collection;
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
  @Autowired private DetailsProvider detailsProvider;

  @Override
  public AbstractAuthenticationToken convert(Jwt jwt) {
    String userId = jwt.getClaim("userId");

    Details details = detailsProvider.findById(userId);

    UserDetails principal =
        User.builder()
            .accountExpired(false)
            .accountLocked(false)
            .credentialsExpired(false)
            .disabled(false)
            .username(details.username().toString())
            .password(details.password().toString())
            .authorities(details.roles().stream().map(SimpleGrantedAuthority::new).toList())
            .build();

    Object credentials = null;

    Collection<? extends GrantedAuthority> authorities = principal.getAuthorities();

    return new UsernamePasswordAuthenticationToken(principal, credentials, authorities);
  }
}
