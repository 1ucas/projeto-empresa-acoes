package self.manobray.rabbitmq.repositories;

import org.springframework.stereotype.Repository;

import self.manobray.rabbitmq.domain.EmailInput;
import self.manobray.rabbitmq.domain.RestClient;

@Repository
public class EmailRepository {
	
	private String BasePath = "/api/v1/email";
	
	public void sendBasicEmail(String destination, String message) {
		EmailInput input = new EmailInput();
		input.setDestination(destination);
		input.setMessage(message);
		new RestClient().post(BasePath, input);
	}
}
