package com.postres.postres_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class PostresAppApplication  extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(PostresAppApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(PostresAppApplication.class);
	}

	@Configuration
	public static class MyConfiguration {

		@Bean
		public WebMvcConfigurer corsConfigurer() {
			return new WebMvcConfigurer() {

				@Override
				public void addCorsMappings(CorsRegistry registry) {
					registry.addMapping("/**")
							.allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
				}
			};
		}
	}
}
