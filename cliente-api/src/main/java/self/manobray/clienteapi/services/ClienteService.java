package self.manobray.clienteapi.services;

import java.util.List;

import self.manobray.clienteapi.domain.Cliente;

public interface ClienteService {
	
	Cliente getById(String id);
	
	List<Cliente> getAll();

	String createNew(Cliente cliente);

}
