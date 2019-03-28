package self.manobray.rabbitmq.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ordem {
	private int orderType; // 1 = compra / 2 = venda
	private String acaoId;
	private float desiredPrice;
	private String userId;
}
