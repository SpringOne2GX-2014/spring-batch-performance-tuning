package io.spring.messagejoblaunch.domain;

/**
 * <p>
 * Domain class representing an image's metadata.
 * </p>
 */
public class ImageSubmission {
	private String fileName;

	public ImageSubmission(final String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	@Override
	public String toString() {
		return new StringBuilder()
				.append("File name: ")
				.append(fileName)
				.toString();
	}
}
