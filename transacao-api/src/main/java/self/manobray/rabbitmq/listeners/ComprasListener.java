package self.manobray.rabbitmq.listeners;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import self.manobray.rabbitmq.config.RabbitMQConfig;
import self.manobray.rabbitmq.domain.Acao;
import self.manobray.rabbitmq.domain.Ordem;
import self.manobray.rabbitmq.repositories.AcoesRepository;
import self.manobray.rabbitmq.repositories.EmailRepository;
import self.manobray.rabbitmq.repositories.OrdersRepository;

@Component
public class ComprasListener {

	static final Logger logger = LoggerFactory.getLogger(ComprasListener.class);

	private OrdersRepository ordersRepository;
	private AcoesRepository acoesRepository;
	private EmailRepository emailRepository;
	
	public ComprasListener(OrdersRepository ordersRepository, AcoesRepository acoesRepository, EmailRepository emailRepository) {
		this.ordersRepository = ordersRepository;
		this.acoesRepository = acoesRepository;
		this.emailRepository = emailRepository;
	}
	
    @RabbitListener(queues = RabbitMQConfig.QUEUE_COMPRAS)
    public void processMessage(Ordem ordemCompraAProcessar) {
    	List<Ordem> ordensDeVendaEmAberto = this.ordersRepository.findByOrderType(2);
        for (Ordem ordemVenda : ordensDeVendaEmAberto) {
			if(ordemVenda.getAcaoId().equals(ordemCompraAProcessar.getAcaoId()) &&
					ordemVenda.getDesiredPrice() <= ordemCompraAProcessar.getDesiredPrice()) {
				atualizarAcao(ordemCompraAProcessar);
				ordersRepository.delete(ordemVenda);
				emailRepository.sendBasicEmail(ordemCompraAProcessar.getUserId(), "Comprou acao por " + ordemCompraAProcessar.getDesiredPrice());
				emailRepository.sendBasicEmail(ordemVenda.getUserId(), "Vendeu acao por " + ordemVenda.getDesiredPrice());
				return;
			}
		}
        this.ordersRepository.save(ordemCompraAProcessar);
    }
    
    private void atualizarAcao(Ordem ordemCompraAProcessar) {
		Acao acao = acoesRepository.findById(ordemCompraAProcessar.getAcaoId()).get();
		acao.setActualValue(ordemCompraAProcessar.getDesiredPrice());
		acao.setLastTransaction(new Date());
		acao.setOwnerId(ordemCompraAProcessar.getUserId());
		acoesRepository.save(acao);
	}
}
