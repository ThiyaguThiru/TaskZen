package com.taskzen.api.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfiguration {
	@Bean
	public OpenAPI taskZenOpenAPI() {
		return new OpenAPI().info(new Info().title("TaskZen API")
				.description("API documentation for Task and Notes management service").version("1.0.0")
				.contact(new Contact().name("TaskZen").url("https://github.com/ThiyaguThiru/TaskZen")));
	}
}
