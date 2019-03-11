package self.manobray.empresaapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import self.manobray.empresaapi.domain.Empresa;
import self.manobray.empresaapi.repositories.EmpresaRepository;

@Service
public class EmpresaServiceImpl implements EmpresaService {
	
	private List<Empresa> empresas = new ArrayList<>();
	private EmpresaRepository empresaRepository;
	
	public EmpresaServiceImpl(EmpresaRepository empresaRepository) {
		super();
		this.empresaRepository = empresaRepository;
	}

	@Override
	public Empresa getById(String id) {
		return this.empresaRepository.findById(id).orElse(null);
	}
	 
	@Override
	public List<Empresa> getAll() {
		return this.empresaRepository.findAll();
	} 

	@Override
	public String createNew(Empresa empresa) {
		return this.empresaRepository.save(empresa).getId();
	}
	
	

}
