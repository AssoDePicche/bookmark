package com.bookmark.common.infrastructure.configuration;

import java.time.format.DateTimeFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DateTimeFormatterConfiguration {
  @Bean
  public DateTimeFormatter dateTimeFormatter() {
    return DateTimeFormatter.ISO_DATE_TIME;
  }
}
