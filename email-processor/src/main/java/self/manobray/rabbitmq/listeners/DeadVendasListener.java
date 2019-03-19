package self.manobray.rabbitmq.listeners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import self.manobray.rabbitmq.config.RabbitMQConfig;
import self.manobray.rabbitmq.domain.Message;
import self.manobray.rabbitmq.services.EmailService;

@Component
public class DeadVendasListener {

private EmailService emailService;
	
	public DeadVendasListener(EmailService emailService) {
		this.emailService = emailService;
	}

	@RabbitListener(queues = RabbitMQConfig.QUEUE_DEAD_COMPRAS)
    public void processMessage(Message message) {
		emailService.sendEmail(message);
    }
}
