package com.java.spring.service;

import java.util.Date;
import java.util.Map;

import com.java.spring.dto.JobResult;

/**
 * Job interface and life cycle.
 * 
 * @author Rija RAMAMPIANDRA.
 *
 */
public interface JobProcessor {
	public JobResult execute(Map<String, String> params) throws Exception;

	public JobResult stop() throws Exception;

	public JobResult getStatus();

	public Date startDate();

	public Date endDate();

}
