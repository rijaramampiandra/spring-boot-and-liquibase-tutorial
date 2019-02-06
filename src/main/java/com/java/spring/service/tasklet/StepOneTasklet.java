package com.java.spring.service.tasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * Sample tasklet.
 * 
 * @author Rija RAMAMPIANDRA.
 *
 */
public class StepOneTasklet implements Tasklet {

	private static final Logger logger = LoggerFactory.getLogger(StepOneTasklet.class);

	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
		logger.info("----------------- Run tasklet -----------------------------");
		return RepeatStatus.FINISHED;
	}
}
