package self.manobray.rabbitmq.controllers.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import self.manobray.rabbitmq.services.EmailService;;

@Api("Api de envio de Emails")
@RestController
@RequestMapping(VendasController.BASE_URL)
public class VendasController {

	public static final String BASE_URL = "/api/v1/email";

	private EmailService emailService;
	
	public VendasController(EmailService emailService) {
		this.emailService = emailService;
	}
}
