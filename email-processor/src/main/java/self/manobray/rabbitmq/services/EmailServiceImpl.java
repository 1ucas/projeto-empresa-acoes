package self.manobray.rabbitmq.services;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import self.manobray.rabbitmq.config.EmailConfig;
import self.manobray.rabbitmq.domain.Cliente;
import self.manobray.rabbitmq.domain.EmailInput;
import self.manobray.rabbitmq.domain.Empresa;
import self.manobray.rabbitmq.repositories.ClienteRepository;
import self.manobray.rabbitmq.repositories.EmpresaRepository;

@Service
public class EmailServiceImpl implements EmailService{
    
	static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
	
	private final ClienteRepository clienteRepository;
	private final EmpresaRepository empresaRepository;
	 
    public EmailServiceImpl(ClienteRepository clienteRepository, EmpresaRepository empresaRepository) {
    	this.clienteRepository = clienteRepository;
        this.empresaRepository = empresaRepository;
    }
	
    @Override
    public void sendEmail(EmailInput input) {
    	Optional<Cliente> cliente = clienteRepository.findById(input.getDestination());
    	if(cliente.isPresent()) {
    		new EmailConfig().sendBasicEmail(cliente.get().getEmail(), input.getMessage());
    	} else {
    		Optional<Empresa> empresa = empresaRepository.findById(input.getDestination());
    		if(empresa.isPresent()) {
    			new EmailConfig().sendBasicEmail(empresa.get().getEmail(), input.getMessage());
    		}
    	}
    }
}
