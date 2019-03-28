package self.manobray.rabbitmq.services;

import self.manobray.rabbitmq.domain.OrderInput;

public interface VendasService {

	void executeOrder(OrderInput input);
}
