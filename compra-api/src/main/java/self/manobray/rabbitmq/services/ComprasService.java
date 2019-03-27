package self.manobray.rabbitmq.services;

import self.manobray.rabbitmq.domain.Message;
import self.manobray.rabbitmq.domain.Ordem;

public interface ComprasService {

	void sendMessage(Ordem order);
}
