package io.spring.messagejoblaunch;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>
 * Simple runner to load the application context and listen for files to be batch processed.
 * </p>
 */
public class FileListener {
	private static final String CONFIG = "/META-INF/spring/config/integration-context.xml";

	public static void main(String[] args) throws Exception {
		new ClassPathXmlApplicationContext(CONFIG);
	}
}
