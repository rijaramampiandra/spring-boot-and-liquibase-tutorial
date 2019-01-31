package com.java.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan(basePackages = { "com.java.spring" })
@PropertySource(value = { "classpath:default-config/application-default.properties" }, ignoreResourceNotFound = true)
public class SpringAndLiquiBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAndLiquiBaseApplication.class, args);
	}
}
