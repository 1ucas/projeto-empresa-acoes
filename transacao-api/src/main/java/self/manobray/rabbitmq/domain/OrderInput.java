package self.manobray.rabbitmq.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderInput {

	private String acaoId;
	private float desiredPrice;
	private String userId;
}
