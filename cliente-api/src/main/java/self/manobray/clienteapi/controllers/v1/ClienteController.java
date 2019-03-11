package self.manobray.clienteapi.controllers.v1;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import self.manobray.clienteapi.domain.Cliente;
import self.manobray.clienteapi.domain.User;
import self.manobray.clienteapi.services.ClienteService;

@Api("Api de Clientes")
@RestController
@RequestMapping(ClienteController.BASE_URL)
public class ClienteController {

	public static final String BASE_URL = "/api/v1/clientes";
	
	private final ClienteService clienteService;
	
	public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
	}
	
	@ApiOperation(value = "Ver a lista de clientes", notes="Retorna a lista de todas os clientes.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> getAll(){
        return clienteService.getAll();
    }
    
	@ApiOperation(value = "Busca o clientes por seu ID")
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Cliente getById(@PathVariable String id){
        return clienteService.getById(id);
    }
	
	@ApiOperation(value = "Cria um novo clientes", notes = "Não é necessário informar o Id do cliente durante a criação.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody Cliente cliente){
        return clienteService.createNew(cliente);
    }
}
