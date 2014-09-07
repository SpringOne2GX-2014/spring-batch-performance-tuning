package io.spring.remotepartitioningmaster;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>
 * Simple job runner to execute a job without any parameters.
 * </p>
 */
public class JobRunner {
	private static final String CONFIG = "/META-INF/spring/config/job-context.xml";

	public static void main(String[] args) throws Exception {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(CONFIG);

		Job job = applicationContext.getBean(Job.class);
		JobLauncher jobLauncher = applicationContext.getBean(JobLauncher.class);

		jobLauncher.run(job, new JobParameters());

		System.exit(0);
	}
}
