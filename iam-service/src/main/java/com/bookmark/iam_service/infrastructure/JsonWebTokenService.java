package com.bookmark.iam_service.infrastructure;

import com.bookmark.iam_service.domain.Identity;
import java.time.Instant;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class JsonWebTokenService {
  private static final long TOKEN_EXPIRATION_SECONDS = 3600L;

  private static final String ISSUER = "spring-security";

  private final JwtEncoder encoder;

  public JsonWebTokenService(JwtEncoder encoder) {
    this.encoder = encoder;
  }

  public String generateToken(Identity identity) {
    Instant now = Instant.now();

    JwtClaimsSet claims = JwtClaimsSet.builder()
                              .issuer(ISSUER)
                              .issuedAt(now)
                              .expiresAt(now.plusSeconds(TOKEN_EXPIRATION_SECONDS))
                              .subject(identity.getUsername().toString())
                              .claim("userId", identity.getId().toString())
                              .build();

    var parameters = JwtEncoderParameters.from(claims);

    return encoder.encode(parameters).getTokenValue();
  }
}
