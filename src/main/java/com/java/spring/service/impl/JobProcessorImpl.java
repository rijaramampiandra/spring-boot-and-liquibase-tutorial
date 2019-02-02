package com.java.spring.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobExecutionNotRunningException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.java.spring.dto.JobResult;
import com.java.spring.dto.JobResult.ResultType;
import com.java.spring.service.JobProcessor;

/**
 * Job implementation.
 * 
 * @author Rija RAMAMPIANDRA.
 *
 */
public abstract class JobProcessorImpl implements JobProcessor {

	private Logger logger = LoggerFactory.getLogger(getClass());

	// COMPLETED, STARTING, STARTED, STOPPING, STOPPED, FAILED, ABANDONED
	public static final List<BatchStatus> LST_STOPPED_STATUS = Arrays.asList(BatchStatus.COMPLETED, BatchStatus.STOPPED,
			BatchStatus.FAILED, BatchStatus.ABANDONED);

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private JobOperator jobOperator;

	private JobExecution jobExecution;

	@Override
	public JobResult execute(Map<String, String> params) throws Exception {

		Job job = getJob();

		logger.info(job.getName() + " execution");

		// Long instanceId = jobOperator.start(job.getName(), null);
		// jobExecution = jobExplorer.getJobExecution(instanceId);

		Map<String, JobParameter> mapJobParameter = new HashMap<String, JobParameter>();
		for (Map.Entry<String, String> curr : params.entrySet()) {
			mapJobParameter.put(curr.getKey(), new JobParameter(curr.getValue()));
		}

		jobExecution = jobLauncher.run(job, new JobParameters(mapJobParameter));

		switch (jobExecution.getStatus()) {
		case ABANDONED:
			return new JobResult(ResultType.RESPONSE_OK, "Processus has been canceled", "");
		case FAILED:
			return new JobResult(ResultType.RESPONSE_ERROR, "Processus KO", getBatchErrorMessage(jobExecution));
		default:
			return new JobResult(ResultType.RESPONSE_OK, "Processus OK", "");
		}

	}

	@Override
	public JobResult getStatus() {

		if (jobExecution == null) {
			return new JobResult(ResultType.RESPONSE_NOT_STARTED, "Executing...", null);
		} else if (jobExecution.isRunning()) {
			return new JobResult(ResultType.RESPONSE_RUNNING, "Executing...", null);
		} else if (jobExecution.getStatus().isUnsuccessful()) {
			return new JobResult(ResultType.RESPONSE_ERROR, "Response Error ", getBatchErrorMessage(jobExecution));
		} else if (jobExecution.getStatus().equals(BatchStatus.COMPLETED)
				|| jobExecution.getStatus().equals(BatchStatus.STOPPED)) {
			return new JobResult(ResultType.RESPONSE_OK, "Processus OK ", null);
		} else {
			return new JobResult(ResultType.RESPONSE_UNKNOWN, "", null);
		}
	}

	public JobResult stop() {

		try {
			// stop framework
			jobOperator.stop(jobExecution.getId());
		} catch (NoSuchJobExecutionException e) {
			logger.warn("Job not exist");
		} catch (JobExecutionNotRunningException e) {
			logger.warn("Job not running");
		}

		// Stop logique
		jobExecution.stop();

		return getStatus();
	}

	private String getBatchErrorMessage(JobExecution jobExecution) {

		String exitDescription = jobExecution.getExitStatus().getExitDescription();

		if (exitDescription != null && exitDescription.length() > 0) {
			return exitDescription;
		} else {

			List<Throwable> exceptions = jobExecution.getAllFailureExceptions();

			if (exceptions != null && exceptions.size() > 0) {
				return exceptions.get(0).getMessage();
			} else {
				return "";
			}
		}

	}

	@Override
	public Date startDate() {
		if (jobExecution != null) {
			return jobExecution.getStartTime();
		}

		return null;
	}

	@Override
	public Date endDate() {
		if (jobExecution != null) {
			return jobExecution.getEndTime();
		}

		return null;
	}

	/**
	 * Provide spring batch job for execution
	 * 
	 * @return
	 */
	protected abstract Job getJob();

}
