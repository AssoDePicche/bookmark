package com.bookmark.catalog_service.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfiguration {
  @Bean
  public RestClient restClient(@Value("${catalog.search.url}") String URL) {
    return RestClient.builder().baseUrl(URL).build();
  }
}
