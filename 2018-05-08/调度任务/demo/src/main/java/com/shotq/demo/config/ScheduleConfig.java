package com.shotq.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduleConfig {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Scheduled(cron = "0/1 * * * * ? ")
	public void recordSchedule() {
		logger.info("schedule start >>>>");
		logger.info("schedule.end <<<<");
	}

}
