package com.java.spring.service.tasklet;

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

	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
		System.out.println("----------------- Run tasklet -----------------------------");
		return RepeatStatus.FINISHED;
	}
}
