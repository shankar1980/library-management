package com.vc.library.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(CorsProperties.class)
@ConditionalOnProperty(prefix = "cors", name = "enabled", havingValue = "true")
public class CorsAutoConfiguration extends WebMvcConfigurationSupport {

	@Autowired
	private CorsProperties properties;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		CorsRegistration corsRegistration = registry.addMapping(properties.getPathPattern());
		corsRegistration
				.allowedOriginPatterns(properties.getAllowedOrigins())
				.allowedHeaders(properties.getAllowedHeaders())
				.maxAge(properties.getMaxAge())
				.allowCredentials(properties.getAllowCredentials());

		if (properties.getAllowedMethods() != null) {
			corsRegistration.allowedMethods(properties.getAllowedMethods());
		}
		if (properties.getExposedHeaders() != null) {
			corsRegistration.exposedHeaders(properties.getExposedHeaders());
		}
	}
}