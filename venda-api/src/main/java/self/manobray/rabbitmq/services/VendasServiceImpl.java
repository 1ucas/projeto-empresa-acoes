package self.manobray.rabbitmq.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import self.manobray.rabbitmq.config.RabbitMQConfig;
import self.manobray.rabbitmq.domain.Message;

@Service
public class VendasServiceImpl implements VendasService{

	private final RabbitTemplate rabbitTemplate;
	 
    public VendasServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    
    @Override
    public void sendMessage(Message message) {
        this.rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_VENDAS, message);
    }
}
