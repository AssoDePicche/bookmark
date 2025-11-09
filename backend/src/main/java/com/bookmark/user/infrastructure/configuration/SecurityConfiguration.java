package com.bookmark.user.infrastructure.configuration;

import com.bookmark.iam.infrastructure.AuthenticationTokenConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@Profile("prod")
public class SecurityConfiguration implements WebMvcConfigurer {
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedMethods("DELETE", "GET", "PATCH", "POST", "PUT", "OPTIONS")
        .allowedHeaders("*")
        .allowedOriginPatterns("*")
        .allowCredentials(true);
  }

  @Bean
  public AuthenticationTokenConverter authenticationTokenConverter() {
    return new AuthenticationTokenConverter();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(
      HttpSecurity security, AuthenticationTokenConverter converter) throws Exception {
    return security.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(request
            -> request
                   .requestMatchers("/api/actuator/**", "/api/iam/authenticate",
                       "/api/users/register", "/swagger-ui/**", "/swagger-ui.html",
                       "/v3/api-docs/**")
                   .permitAll()
                   .anyRequest()
                   .authenticated())
        .oauth2ResourceServer(
            server -> server.jwt(jwt -> jwt.jwtAuthenticationConverter(converter)))
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .build();
  }
}
