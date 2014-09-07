package io.spring.messagejoblaunch.gateway;

/**
 * <p>
 * Sends String based notifications to a receiver.
 * </p>
 */
public interface NotificationSender {
	void sendNotification(String notification);
}
