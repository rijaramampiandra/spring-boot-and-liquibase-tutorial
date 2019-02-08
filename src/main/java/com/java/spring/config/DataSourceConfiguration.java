package com.java.spring.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * DB Access.
 * 
 * @author Rija RAMAMPIANDRA.
 *
 */
@Configuration
@EnableJpaRepositories("com.java.spring.repository")
@EnableTransactionManagement
public class DataSourceConfiguration {

	@ConfigurationProperties(prefix = "spring.datasource")
	@Bean(name = "datasource")
	@Primary
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

}