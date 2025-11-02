package com.bookmark.common.infrastructure.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(description = "OpenAPI Docs", title = "OpenAPI Specs"))
public class OpenAPIConfiguration {}
