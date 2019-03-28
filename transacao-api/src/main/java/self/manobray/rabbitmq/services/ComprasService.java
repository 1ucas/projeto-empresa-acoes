package self.manobray.rabbitmq.services;

import self.manobray.rabbitmq.domain.OrderInput;

public interface ComprasService {

	void executeOrder(OrderInput input);
}
