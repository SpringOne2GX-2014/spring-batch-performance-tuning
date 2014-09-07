package io.spring.messagejoblaunch.reader;

import io.spring.messagejoblaunch.domain.ImageSubmission;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.InitializingBean;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * {@link org.springframework.batch.item.ItemReader} implementation to read
 * image file names from the provided directory and return a
 * {@link io.spring.messagejoblaunch.domain.ImageSubmission} representing the image.
 * </p>
 */
public class DirectoryItemReader implements ItemReader<ImageSubmission>, InitializingBean {
	private final String fileSuffix;
	private final String directoryPath;
	private final List<String> foundFiles = Collections.synchronizedList(new ArrayList<String>());

	public DirectoryItemReader(final String directoryPath, final String fileSuffix) {
		this.fileSuffix = fileSuffix;
		this.directoryPath = directoryPath;
	}

	@Override
	public ImageSubmission read() throws Exception {
		if (!foundFiles.isEmpty()) {
			return new ImageSubmission(foundFiles.remove(0));
		}

		synchronized (foundFiles) {
			final Iterator files = foundFiles.iterator();

			if (files.hasNext()) {
				return new ImageSubmission(foundFiles.remove(0));
			}
		}

		return null;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		final File[] foundFiles = getFiles();

		if (foundFiles != null) {
			for (final File file : foundFiles) {
				if (file.isFile()) {
					this.foundFiles.add(file.getName());
				}
			}
		}
	}

	private File[] getFiles() {
		final File folder = new File(directoryPath);

		return folder.listFiles(new FileFilter() {
			@Override
			public boolean accept(final File pathname) {
				return pathname.getName().endsWith(fileSuffix);
			}
		});
	}
}
