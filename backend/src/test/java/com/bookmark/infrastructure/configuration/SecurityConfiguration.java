package com.bookmark.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("test")
public class SecurityConfiguration {
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
    return security.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(request -> request.anyRequest().permitAll())
        .build();
  }
}
