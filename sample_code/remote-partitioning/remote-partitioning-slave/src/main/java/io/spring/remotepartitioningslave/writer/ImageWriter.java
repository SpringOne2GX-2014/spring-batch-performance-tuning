package io.spring.remotepartitioningslave.writer;

import io.spring.remotepartitioningslave.domain.ProcessedImage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemWriter;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.List;

/**
 * <p>
 * {@link org.springframework.batch.item.ItemWriter} implementation that writes
 * the {@link io.spring.remotepartitioningslave.domain.ProcessedImage}'s to the provided path
 * using the specified image format.
 * </p>
 */
public class ImageWriter implements ItemWriter<ProcessedImage> {
	private static final Log LOG = LogFactory.getLog(ImageWriter.class);

	private final String path;
	private final String formatName;

	public ImageWriter(final String path, final String formatName) {
		this.path = path;
		this.formatName = formatName;
	}

	public void write(final List<? extends ProcessedImage> processedImages) throws Exception {
		LOG.info("Writing: " + processedImages.size() + " images");

		for (ProcessedImage processedImage : processedImages) {
			ImageIO.write(processedImage.getImage(), formatName, new File(path + processedImage.getFileName()));
		}
	}
}
