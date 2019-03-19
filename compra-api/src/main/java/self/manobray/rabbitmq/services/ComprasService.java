package self.manobray.rabbitmq.services;

import self.manobray.rabbitmq.domain.Message;

public interface ComprasService {

	void sendMessage(Message message);
}
