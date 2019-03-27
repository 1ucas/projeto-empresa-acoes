package self.manobray.clienteapi.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ordem {
	private int orderType; // 0 = compra / 1 = venda
	private String acaoId;
	private float acaoPrice;
	private String userId;
}
