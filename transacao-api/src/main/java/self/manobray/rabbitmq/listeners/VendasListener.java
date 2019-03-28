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
public class VendasListener {

	static final Logger logger = LoggerFactory.getLogger(VendasListener.class);
	
	private OrdersRepository ordersRepository;
	private AcoesRepository acoesRepository;
	private EmailRepository emailRepository;
	
	public VendasListener(OrdersRepository ordersRepository, AcoesRepository acoesRepository, EmailRepository emailRepository) {
		this.ordersRepository = ordersRepository;
		this.acoesRepository = acoesRepository;
		this.emailRepository = emailRepository;
	}

    @RabbitListener(queues = RabbitMQConfig.QUEUE_VENDAS)
    public void processMessage(Ordem ordemVendaAProcessar) {
    	List<Ordem> ordensDeCompraEmAberto = this.ordersRepository.findByOrderType(1);
        for (Ordem ordemCompra : ordensDeCompraEmAberto) {
			if(ordemCompra.getAcaoId().equals(ordemVendaAProcessar.getAcaoId()) &&
					ordemCompra.getDesiredPrice() >= ordemVendaAProcessar.getDesiredPrice()) {
				atualizarAcao(ordemVendaAProcessar);
				ordersRepository.delete(ordemCompra);
				emailRepository.sendBasicEmail(ordemVendaAProcessar.getUserId(), "Vendeu acao por " + ordemVendaAProcessar.getDesiredPrice());
				emailRepository.sendBasicEmail(ordemCompra.getUserId(), "Comprou acao por " + ordemCompra.getDesiredPrice());
				return;
			}
		}
        this.ordersRepository.save(ordemVendaAProcessar);
    }

	private void atualizarAcao(Ordem ordemVendaAProcessar) {
		Acao acao = acoesRepository.findById(ordemVendaAProcessar.getAcaoId()).get();
		acao.setActualValue(ordemVendaAProcessar.getDesiredPrice());
		acao.setLastTransaction(new Date());
		acao.setOwnerId(ordemVendaAProcessar.getUserId());
		acoesRepository.save(acao);
	}
}
