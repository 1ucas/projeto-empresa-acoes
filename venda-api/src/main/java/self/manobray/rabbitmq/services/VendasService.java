package self.manobray.rabbitmq.services;

import self.manobray.rabbitmq.domain.Message;

public interface VendasService {

	void sendMessage(Message message);
}
