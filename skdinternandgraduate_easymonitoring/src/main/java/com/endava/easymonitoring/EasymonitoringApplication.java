package com.endava.easymonitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class EasymonitoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasymonitoringApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/rest/login/session").allowedOrigins("http://localhost:9000");
				registry.addMapping("/rest/api/search/{projectId}").allowedOrigins("http://localhost:9000");

			}
		};
	}
}
