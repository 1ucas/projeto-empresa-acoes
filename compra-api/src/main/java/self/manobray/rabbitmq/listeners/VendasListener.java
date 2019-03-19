package self.manobray.rabbitmq.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import self.manobray.rabbitmq.config.RabbitMQConfig;
import self.manobray.rabbitmq.domain.Message;

@Component
public class VendasListener {

	static final Logger logger = LoggerFactory.getLogger(VendasListener.class);

    @RabbitListener(queues = RabbitMQConfig.QUEUE_VENDAS)
    public void processMessage(Message message) {
        logger.info("Compras Api Ouviu:");
        logger.info("Subject:" + message.getSubject());
        logger.info("Body:" + message.getBody());
    }
}
