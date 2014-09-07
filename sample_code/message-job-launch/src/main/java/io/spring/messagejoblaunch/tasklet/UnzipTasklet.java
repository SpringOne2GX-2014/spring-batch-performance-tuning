package io.spring.messagejoblaunch.tasklet;

import net.lingala.zip4j.core.ZipFile;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * <p>
 * {@link org.springframework.batch.core.step.tasklet.Tasklet} implementation
 * to unzip the provided archive.
 * </p>
 */
public class UnzipTasklet implements Tasklet {
	private final String file;
	private final String destination;

	public UnzipTasklet(final String file, final String destination) {
		this.file = file;
		this.destination = destination;
	}

	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
		try {
			ZipFile zipFile = new ZipFile(file);
			zipFile.extractAll(destination);
		} catch (Exception e) {
			throw new RuntimeException("Failed to unzip file: " + file, e);
		}

		return RepeatStatus.FINISHED;
	}
}
