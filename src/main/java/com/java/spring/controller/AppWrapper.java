package com.java.spring.controller;

import org.springframework.batch.core.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.java.spring.service.impl.JobProcessorImpl;

/**
 * Job controller.
 * 
 * @author Rija RAMAMPIANDRA.
 *
 */
@Component
@RestController
public class AppWrapper extends JobProcessorImpl {

	@Autowired
	private Job job;

	@Override
	public Job getJob() {
		return job;
	}

}
