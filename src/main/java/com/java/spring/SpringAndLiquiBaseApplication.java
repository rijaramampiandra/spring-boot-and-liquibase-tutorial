package com.java.spring;

import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * Application configuration.
 * 
 * @author Rija RAMAMPIANDRA.
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.java.spring" })
@PropertySource(value = { "classpath:default-config/application-default.properties" }, ignoreResourceNotFound = true)
public class SpringAndLiquiBaseApplication {

	@Bean
	public JobLauncherTestUtils jobLauncherTestUtils() {
		return new JobLauncherTestUtils();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringAndLiquiBaseApplication.class, args);
	}
}
