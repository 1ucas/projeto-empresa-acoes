package self.manobray.clienteapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import self.manobray.clienteapi.domain.Cliente;
import self.manobray.clienteapi.repositories.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	private List<Cliente> clientes = new ArrayList<>();
	private ClienteRepository clienteRepository;
	
	public ClienteServiceImpl(ClienteRepository clienteRepository) {
		super();
		this.clienteRepository = clienteRepository;
	}

	@Override
	public Cliente getById(String id) {
		return this.clienteRepository.findById(id).orElse(null);
	}
	 
	@Override
	public List<Cliente> getAll() {
		return this.clienteRepository.findAll();
	} 

	@Override
	public String createNew(Cliente cliente) {
		return this.clienteRepository.save(cliente).getId();
	}
	
	

}
