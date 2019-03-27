package self.manobray.rabbitmq.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import self.manobray.rabbitmq.domain.Message;
import self.manobray.rabbitmq.services.EmailService;;

@Api("Api de envio de Emails")
@RestController
@RequestMapping(EmailsController.BASE_URL)
public class EmailsController {

	public static final String BASE_URL = "/api/v1/email";

	private EmailService emailService;
	
	public EmailsController(EmailService emailService) {
		this.emailService = emailService;
	}
	
	@ApiOperation(value = "Envia um email com assunto e corpo", notes = "")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Message mensagem){
		emailService.sendEmail(mensagem);
    }
}
