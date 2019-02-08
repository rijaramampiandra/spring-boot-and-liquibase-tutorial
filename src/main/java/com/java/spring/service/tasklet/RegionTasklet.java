package com.java.spring.service.tasklet;

import java.util.Calendar;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.java.spring.entity.Region;
import com.java.spring.service.RegionService;

/**
 * Sample tasklet.
 * 
 * @author Rija RAMAMPIANDRA.
 *
 */
public class RegionTasklet implements Tasklet {

	private static final Logger logger = LoggerFactory.getLogger(RegionTasklet.class);

	private RegionService regionService;

	public RegionTasklet(RegionService regionService) {
		this.regionService = regionService;
	}

	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
		logger.info("----------------- Run RegionTasklet -----------------------------");
		final Region regionOnDb = regionService.save(new Region.Builder().withDescription("Antananarivo")
				.onCreateDate(Calendar.getInstance().getTime()).build());

		if (regionOnDb.getId() != null) {
			final Optional<Region> response = regionService.findById(regionOnDb.getId());
			logger.info("----------------- Region created -----------------------------" + response.toString());
		}

		return RepeatStatus.FINISHED;
	}
}
