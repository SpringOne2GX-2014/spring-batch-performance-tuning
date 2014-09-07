package io.spring.messagejoblaunch.transformer;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.integration.launch.JobLaunchRequest;
import org.springframework.messaging.Message;

import java.io.File;
import java.util.Date;

/**
 * <p>
 * Class used as a transformer that takes the received {@link org.springframework.messaging.Message}
 * and invokes a batch job with its payload.
 * </p>
 */
public class FileMessageToJobRequest {
	private Job job;
	private String fileName;

	public FileMessageToJobRequest(final String fileName, final Job job) {
		this.job = job;
		this.fileName = fileName;
	}

	public JobLaunchRequest toRequest(Message<File> message) {
		JobParametersBuilder jobParametersBuilder =
				new JobParametersBuilder();

		jobParametersBuilder
				.addString(fileName, message.getPayload().getAbsolutePath())
				.addDate("run.date", new Date());

		return new JobLaunchRequest(job, jobParametersBuilder.toJobParameters());
	}
}
