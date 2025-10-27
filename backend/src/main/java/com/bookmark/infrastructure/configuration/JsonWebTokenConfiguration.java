package com.bookmark.infrastructure.configuration;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

@Configuration
public class JsonWebTokenConfiguration {
  @Bean
  public JwtDecoder jwtDecoder(@Value("${jwt.public.key}") RSAPublicKey publicKey) {
    return NimbusJwtDecoder.withPublicKey(publicKey).build();
  }

  @Bean
  public JwtEncoder jwtEncoder(@Value("${jwt.public.key}") RSAPublicKey publicKey,
      @Value("${jwt.private.key}") RSAPrivateKey privateKey) {
    var key = new RSAKey.Builder(publicKey).privateKey(privateKey).build();

    var keySet = new ImmutableJWKSet<>(new JWKSet(key));

    return new NimbusJwtEncoder(keySet);
  }
}
