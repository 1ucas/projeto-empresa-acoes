package self.manobray.rabbitmq.services;

import self.manobray.rabbitmq.domain.Message;

public interface EmailService {

	void sendEmail(Message message);
}
