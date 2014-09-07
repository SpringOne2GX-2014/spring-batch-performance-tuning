package io.spring.messagejoblaunch.listener;

import io.spring.messagejoblaunch.gateway.NotificationSender;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * {@link org.springframework.batch.core.JobExecutionListener} implementation
 * that logs the duration of the job.
 * </p>
 */
public class JobDurationListener implements JobExecutionListener {
	private final NotificationSender notificationSender;

	private StopWatch stopWatch;

	public JobDurationListener(final NotificationSender notificationSender) {
		this.notificationSender = notificationSender;
	}

	public void beforeJob(JobExecution jobExecution) {
		stopWatch = new StopWatch();
		stopWatch.start("Processing image submissions");
	}

	public void afterJob(JobExecution jobExecution) {
		stopWatch.stop();

		long duration = stopWatch.getLastTaskTimeMillis();

		notificationSender.sendNotification(String.format("Image processing job ran for: %d minutes, %d seconds.",
				TimeUnit.MILLISECONDS.toMinutes(duration),
				TimeUnit.MILLISECONDS.toSeconds(duration) -
						TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration))
		));
	}
}
