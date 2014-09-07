package io.spring.remotechunkingdomain.domain;

import java.io.Serializable;

/**
 * <p>
 * Domain class representing a submitted image's metadata.
 * </p>
 */
public class ImageSubmission implements Serializable {
	private long id;
	private String fileName;

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(final String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return new StringBuilder()
				.append("ID: ")
				.append(id)
				.append(" File name: ")
				.append(fileName)
				.toString();
	}
}
