package self.manobray.rabbitmq.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import self.manobray.rabbitmq.config.RabbitMQConfig;
import self.manobray.rabbitmq.domain.Ordem;
import self.manobray.rabbitmq.domain.OrderInput;

@Service
public class VendasServiceImpl implements VendasService{

	private final RabbitTemplate rabbitTemplate;
	 
    public VendasServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    
    @Override
    public void executeOrder(OrderInput input) { 
        Ordem ordem = new Ordem();
    	ordem.setAcaoId(input.getAcaoId());
    	ordem.setDesiredPrice(input.getDesiredPrice());
    	ordem.setOrderType(2);
    	ordem.setUserId(input.getUserId());
    	sendMessage(ordem);
    }

	private void sendMessage(Ordem ordem) {
		this.rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_VENDAS, ordem);
	}
}
