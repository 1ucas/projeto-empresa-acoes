package self.manobray.rabbitmq.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import self.manobray.rabbitmq.config.RabbitMQConfig;
import self.manobray.rabbitmq.domain.Message;
import self.manobray.rabbitmq.domain.Ordem;

@Component
public class VendasListener {

	static final Logger logger = LoggerFactory.getLogger(VendasListener.class);

    @RabbitListener(queues = RabbitMQConfig.QUEUE_VENDAS)
    public void processMessage(Ordem order) {
        logger.info("Compras Api Ouviu:");
        logger.info("Subject: acao de Id" + order.getUserId());
        logger.info("Body:" + order.getAcaoPrice());
    }
}
