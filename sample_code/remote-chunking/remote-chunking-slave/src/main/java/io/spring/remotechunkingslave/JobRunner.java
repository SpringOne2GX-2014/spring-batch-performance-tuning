package io.spring.remotechunkingslave;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>
 * Sample slave node.
 * </p>
 *
 * @author Chris Schaefer
 */
public class JobRunner {
	private static final String CONFIG = "/META-INF/spring/config/job-context.xml";

	public static void main(String[] args) {
		new ClassPathXmlApplicationContext(CONFIG);
	}
}
