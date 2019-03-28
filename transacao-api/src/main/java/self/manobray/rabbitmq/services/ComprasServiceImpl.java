package self.manobray.rabbitmq.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import self.manobray.rabbitmq.config.RabbitMQConfig;
import self.manobray.rabbitmq.domain.Ordem;
import self.manobray.rabbitmq.domain.OrderInput;

@Service
public class ComprasServiceImpl implements ComprasService{

	private final RabbitTemplate rabbitTemplate;
	 
    public ComprasServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    
    @Override
    public void executeOrder(OrderInput input) {
    	Ordem ordem = new Ordem();
    	ordem.setAcaoId(input.getAcaoId());
    	ordem.setDesiredPrice(input.getDesiredPrice());
    	ordem.setOrderType(1);
    	ordem.setUserId(input.getUserId());
    	sendMessage(ordem);
    }

	private void sendMessage(Ordem ordem) {
		this.rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_COMPRAS, ordem);
	}
    
    
}
