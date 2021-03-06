package com.java.spring.controller;

import java.util.UUID;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import lombok.extern.slf4j.Slf4j;

/**
 * To run the JOB on http://localhost:8080/launchjob
 * 
 * @author Rija RAMAMPIANDRA.
 *
 */
@RestController
@RequestMapping("/")
@SwaggerDefinition(tags = { @Tag(name = "/", description = "Run Job.") })
@Slf4j
public class JobLauncherController {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	@GetMapping(value = { "/launchjob" })
	@ApiOperation(value = "RUN the job.")
	public String handle() {
		JobExecution jobExecution = null;
		try {
			jobExecution = jobLauncher.run(job,
					new JobParametersBuilder().addString("time", UUID.randomUUID().toString()).toJobParameters());
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return "jobExecution's info: Id = " + jobExecution.getId() + " ,status = " + jobExecution.getExitStatus();
	}
}
