package self.manobray.rabbitmq.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import self.manobray.rabbitmq.domain.OrderInput;
import self.manobray.rabbitmq.services.VendasService;;

@Api("Api de Venda de Acoes")
@RestController
@RequestMapping(VendasController.BASE_URL)
public class VendasController {

	public static final String BASE_URL = "/api/v1/acoes/vendas";
	
	private VendasService vendasService;
	
	public VendasController(VendasService vendasService) {
		this.vendasService = vendasService;
	}

	@ApiOperation(value = "Cria uma nova ordem de venda", notes="Cria uma nova ordem de venda de cliente.")
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createNewGarage(@RequestBody OrderInput input){
		vendasService.executeOrder(input);
        return "Message sent";
    }
}
