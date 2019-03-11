package self.manobray.restswagger.services;

import java.util.List;

import self.manobray.restswagger.domain.Empresa;

public interface EmpresaService {
	
	Empresa getById(String id);
	
	List<Empresa> getAll();

	String createNew(Empresa empresa);

}
