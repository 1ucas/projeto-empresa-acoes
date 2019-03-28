package self.manobray.rabbitmq.services;

import self.manobray.rabbitmq.domain.EmailInput;

public interface EmailService {

	void sendEmail(EmailInput input);
}
