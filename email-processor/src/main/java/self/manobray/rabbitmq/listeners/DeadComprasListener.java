package self.manobray.rabbitmq.listeners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import self.manobray.rabbitmq.config.RabbitMQConfig;
import self.manobray.rabbitmq.domain.EmailInput;
import self.manobray.rabbitmq.domain.Ordem;
import self.manobray.rabbitmq.services.EmailService;

@Component
public class DeadComprasListener {
	
	private EmailService emailService;
	
	public DeadComprasListener(EmailService emailService) {
		this.emailService = emailService;
	}

	@RabbitListener(queues = RabbitMQConfig.QUEUE_DEAD_COMPRAS)
    public void processMessage(Ordem ordemMorta) {
		EmailInput emailInput = new EmailInput();
		emailInput.setDestination(ordemMorta.getUserId());
		emailInput.setMessage("A sua ordem de compra foi rejeitada.");
		emailService.sendEmail(emailInput);
    }
}
