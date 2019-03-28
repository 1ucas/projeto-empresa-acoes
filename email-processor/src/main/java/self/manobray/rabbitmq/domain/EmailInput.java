package self.manobray.rabbitmq.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailInput {
	private String destination;
	private String message;
}
