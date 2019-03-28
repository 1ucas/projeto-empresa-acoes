package self.manobray.rabbitmq.listeners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import self.manobray.rabbitmq.config.RabbitMQConfig;
import self.manobray.rabbitmq.domain.EmailInput;
import self.manobray.rabbitmq.domain.Message;
import self.manobray.rabbitmq.domain.Ordem;
import self.manobray.rabbitmq.services.EmailService;

@Component
public class DeadVendasListener {

private EmailService emailService;
	
	public DeadVendasListener(EmailService emailService) {
		this.emailService = emailService;
	}

	@RabbitListener(queues = RabbitMQConfig.QUEUE_DEAD_VENDAS)
	public void processMessage(Ordem ordemMorta) {
		try {
			EmailInput emailInput = new EmailInput();
			emailInput.setDestination(ordemMorta.getUserId()); 
			emailInput.setMessage("A sua ordem de venda foi rejeitada.");
			emailService.sendEmail(emailInput);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
