package com.java.spring.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.ListableJobLocator;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.java.spring.service.RegionService;
import com.java.spring.service.tasklet.RegionTasklet;

/**
 * Job process config.
 * 
 * @author Rija RAMAMPIANDRA.
 *
 */
@Configuration
@EnableBatchProcessing
public class ProcessJobConfig extends DefaultBatchConfigurer {

	private final static String JOB_NAME = "JOB_NAME";

	@Autowired
	private JobBuilderFactory jobBuilders;

	@Autowired
	private StepBuilderFactory stepBuilders;

	@Autowired
	private RegionService regionService;

	@Autowired(required = false)
	@Qualifier("datasource")
	public void setDataSource(DataSource dataSource) {
	}

	@Bean
	public SimpleJobOperator jobOperator(JobExplorer jobExplorer, JobLauncher jobLauncher,
			ListableJobLocator jobRegistry, JobRepository jobRepository) throws Exception {

		final SimpleJobOperator factory = new SimpleJobOperator();
		factory.setJobExplorer(jobExplorer);
		factory.setJobLauncher(jobLauncher);
		factory.setJobRegistry(jobRegistry);
		factory.setJobRepository(jobRepository);
		return factory;
	}

	@Bean
	public RegionTasklet stepOneTasklet() {
		return new RegionTasklet(regionService);
	}

	@Bean("stepOne")
	Step stepOne() {
		return stepBuilders.get("stepOne").tasklet(stepOneTasklet()).allowStartIfComplete(true).build();
	}

	public static String getJobName() {
		return JOB_NAME;
	}

	@Bean
	public Job processJob() throws Exception {
		return jobBuilders.get(getJobName()).start(stepOne()).build();
	}
}
