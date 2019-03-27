package self.manobray.rabbitmq.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import self.manobray.rabbitmq.config.RabbitMQConfig;
import self.manobray.rabbitmq.domain.Ordem;
import self.manobray.rabbitmq.domain.Message;

@Service
public class ComprasServiceImpl implements ComprasService{

	private final RabbitTemplate import self.manobray.rabbitmq.domain.Ordem;;
	 
    public ComprasServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    
    @Override
    public void sendMessage(Ordem order) {
        this.rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_COMPRAS, order);
    }
}
