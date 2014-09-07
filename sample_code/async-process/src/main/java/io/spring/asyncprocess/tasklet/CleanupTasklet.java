package io.spring.asyncprocess.tasklet;

import org.apache.commons.io.FileUtils;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.io.File;

/**
 * <p>
 * {@link org.springframework.batch.core.step.tasklet.Tasklet} implementation
 * used to do any cleanup needed before the job runs.
 * </p>
 */
public class CleanupTasklet implements Tasklet {
	private final String processedImagePath;

	public CleanupTasklet(final String processedImagePath) {
		this.processedImagePath = processedImagePath;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		FileUtils.cleanDirectory(new File(processedImagePath));

		return RepeatStatus.FINISHED;
	}
}
