package self.manobray.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

@Configuration
public class RabbitMQConfig implements RabbitListenerConfigurer {

    public static final String QUEUE_DEAD_COMPRAS = "dead-compras-queue";
    public static final String EXCHANGE_DEAD_COMPRAS = "dead-compras-exchange";
    
    public static final String QUEUE_DEAD_VENDAS = "dead-vendas-queue";
    public static final String EXCHANGE_DEAD_VENDAS = "dead-vendas-exchange";
 
    @Bean
    Queue deadComprasQueue() {
    	return QueueBuilder.durable(QUEUE_DEAD_COMPRAS)
                .build();
    }
    
    @Bean
    Queue deadVendasQueue() {
    	return QueueBuilder.durable(QUEUE_DEAD_VENDAS)
                .build();
    }
 
    @Bean
    Exchange deadComprasExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_DEAD_COMPRAS).build();
    }
    
    @Bean
    Exchange deadVendasExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_DEAD_VENDAS).build();
    }
 
    @Bean
    Binding deadComprasBinding(Queue deadComprasQueue, TopicExchange deadComprasExchange) {
        return BindingBuilder.bind(deadComprasQueue).to(deadComprasExchange).with(QUEUE_DEAD_COMPRAS);
    }
    
    @Bean
    Binding deadVendasBinding(Queue deadVendasQueue, TopicExchange deadVendasExchange) {
        return BindingBuilder.bind(deadVendasQueue).to(deadVendasExchange).with(QUEUE_DEAD_VENDAS);
    }
    
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }
 
    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    
    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar register) {
        register.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }
 
    @Bean
    MessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory messageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory();
        messageHandlerMethodFactory.setMessageConverter(consumerJackson2MessageConverter());
        return messageHandlerMethodFactory;
    }
 
    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }
}
