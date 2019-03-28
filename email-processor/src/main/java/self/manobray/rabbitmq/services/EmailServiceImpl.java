package self.manobray.rabbitmq.services;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import self.manobray.rabbitmq.config.EmailConfig;
import self.manobray.rabbitmq.domain.Cliente;
import self.manobray.rabbitmq.domain.EmailInput;
import self.manobray.rabbitmq.repositories.ClienteRepository;

@Service
public class EmailServiceImpl implements EmailService{
    
	static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
	
	private final ClienteRepository clienteRepository;
	 
    public EmailServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
	
    @Override
    public void sendEmail(EmailInput input) {
    	Optional<Cliente> cliente = clienteRepository.findById(input.getDestination());
    	if(cliente.isPresent())
    		new EmailConfig().sendBasicEmail(cliente.get().getEmail(), input.getMessage());
    }
}
