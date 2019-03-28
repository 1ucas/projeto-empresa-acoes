package self.manobray.rabbitmq.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class Ordem {
	
	@Id
	private String id;
	
	private int orderType; // 1 = compra / 2 = venda
	private String acaoId;
	private float desiredPrice;
	private String userId;
}
