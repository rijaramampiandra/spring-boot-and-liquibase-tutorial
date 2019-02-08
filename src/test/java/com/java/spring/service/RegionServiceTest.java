package com.java.spring.service;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.java.spring.SpringAndLiquiBaseApplication;
import com.java.spring.entity.Region;
import com.java.spring.utils.DateUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringAndLiquiBaseApplication.class)
// To active test profile and use application-test.properties
@ActiveProfiles("test")
public class RegionServiceTest {
	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Test
	public void launchJob() throws Exception {
		assertEquals(BatchStatus.COMPLETED, jobLauncherTestUtils.launchJob().getStatus());

	}

	@Autowired
	private RegionService regionService;

	@Test
	public void testJob() {
		final Optional<Region> region = regionService.findById(1);
		assertEquals("Check the ID.", new Integer(1), region.get().getId());
		assertEquals("Check the region on liquibase.", "AntananarivoTEST", region.get().getDescription());
		assertEquals("The creation date.", "2019-01-21 14:56",
				DateUtils.format(region.get().getCreateDate(), "yyyy-MM-dd HH:mm"));
	}

}
