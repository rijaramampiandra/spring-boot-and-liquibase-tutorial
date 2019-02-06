package com.java.spring.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.java.spring.controller")).paths(PathSelectors.any()).build()
				.apiInfo(new ApiInfo("Spring Boot REST API.", "Spring Boot REST API.", "1.0", "Terms of service",
						new Contact("Tutorial", "", "rija.ramampiandra@gmail.com"), "Java Expert Developper",
						"https://www.linkedin.com/in/njarahery-ramampiandra-7893a696", new ArrayList<>()));
	}
}
