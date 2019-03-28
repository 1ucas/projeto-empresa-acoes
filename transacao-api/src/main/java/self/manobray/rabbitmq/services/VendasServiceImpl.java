package self.manobray.rabbitmq.services;

import java.util.Optional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.repository.query.ParameterOutOfBoundsException;
import org.springframework.stereotype.Service;

import self.manobray.rabbitmq.config.RabbitMQConfig;
import self.manobray.rabbitmq.domain.Acao;
import self.manobray.rabbitmq.domain.Ordem;
import self.manobray.rabbitmq.domain.OrderInput;
import self.manobray.rabbitmq.repositories.AcoesRepository;

@Service
public class VendasServiceImpl implements VendasService{

	private final RabbitTemplate rabbitTemplate;
	private AcoesRepository acoesRepository;
	 
    public VendasServiceImpl(RabbitTemplate rabbitTemplate, AcoesRepository acoesRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.acoesRepository = acoesRepository;
    }
    
    @Override
    public void executeOrder(OrderInput input) { 
        Ordem ordem = new Ordem();
    	ordem.setAcaoId(input.getAcaoId());
    	ordem.setDesiredPrice(input.getDesiredPrice());
    	ordem.setOrderType(2);
    	ordem.setUserId(input.getUserId());
    	Optional<Acao> acaoAtual = acoesRepository.findById(input.getAcaoId());
    	if(acaoAtual.isPresent() && acaoAtual.get().getOwnerId().equals(input.getUserId()))
    		sendMessage(ordem);
    	else
			throw new ParameterOutOfBoundsException("Você não tem autorização para executar essa ação.", null);
    }

	private void sendMessage(Ordem ordem) {
		this.rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_VENDAS, ordem);
	}
}
