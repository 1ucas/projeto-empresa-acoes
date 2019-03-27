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
import self.manobray.rabbitmq.domain.Ordem;
import self.manobray.rabbitmq.services.ComprasService;

@Api("Api de Compra de Acoes")
@RestController
@RequestMapping(ComprasController.BASE_URL)
public class ComprasController {

	public static final String BASE_URL = "/api/v1/acoes/compras";
	
	private ComprasService comprasService;
	
	public ComprasController(ComprasService comprasService) {
		this.comprasService = comprasService;
	}

	@ApiOperation(value = "Cria uma nova ordem de compra", notes="Cria uma nova ordem de compra de cliente.")
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createNewGarage(@RequestBody Ordem order){
		comprasService.sendMessage(order);
        return "Message sent";
    }
}
