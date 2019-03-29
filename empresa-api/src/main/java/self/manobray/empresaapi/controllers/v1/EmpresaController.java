package self.manobray.empresaapi.controllers.v1;

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
import self.manobray.empresaapi.domain.Empresa;
import self.manobray.empresaapi.services.EmpresaService;

@Api("Api de Empresas")
@RestController
@RequestMapping(EmpresaController.BASE_URL)
public class EmpresaController {

	public static final String BASE_URL = "/api/v1/empresas";
	
	private final EmpresaService empresaService;
	
	public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
	}
	
	@ApiOperation(value = "Ver a lista de empresas", notes="Retorna a lista de todas as empresas.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Empresa> getAll(){
        return empresaService.getAll();
    }
    
	@ApiOperation(value = "Busca a empresa por seu ID")
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Empresa getById(@PathVariable String id){
        return empresaService.getById(id);
    }
	
	@ApiOperation(value = "Cria uma nova empresa", notes = "Não é necessário informar o Id da empresa durante a criação.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody Empresa empresa){
        return empresaService.createNew(empresa);
    }
}
