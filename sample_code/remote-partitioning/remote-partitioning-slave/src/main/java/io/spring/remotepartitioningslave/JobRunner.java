package io.spring.remotepartitioningslave;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>
 * Job runner simply which loads the context for the slave nodes.
 * </p>
 */
public class JobRunner {
	private static final String CONFIG = "/META-INF/spring/config/job-context.xml";

	public static void main(String[] args) throws Exception {
		new ClassPathXmlApplicationContext(CONFIG);
	}
}
