package myproject.scheduler;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Slf4j
@Component
public class MyScheduler {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Scheduled(cron = "#{@schedulerCronExample1}")
	public void schedule1() {
		logger.info("schedule1 동작하고있음 : {}", Calendar.getInstance().getTime());
	}
}
