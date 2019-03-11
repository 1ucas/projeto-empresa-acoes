package self.manobray.empresaapi.services;

import java.util.List;

import self.manobray.empresaapi.domain.Empresa;

public interface EmpresaService {
	
	Empresa getById(String id);
	
	List<Empresa> getAll();

	String createNew(Empresa empresa);

}
