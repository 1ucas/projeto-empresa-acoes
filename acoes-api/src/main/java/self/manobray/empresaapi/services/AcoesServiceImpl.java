package self.manobray.empresaapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import self.manobray.empresaapi.domain.Acao;
import self.manobray.empresaapi.repositories.AcoesRepository;

@Service
public class AcoesServiceImpl implements AcoesService {
	
	private AcoesRepository acoesRepository;
	
	public AcoesServiceImpl(AcoesRepository acoesRepository) {
		super();
		this.acoesRepository = acoesRepository;
	}

	@Override
	public Acao getById(String id) {
		return this.acoesRepository.findById(id).orElse(null);
	}
	 
	@Override
	public List<Acao> getAll() {
		return this.acoesRepository.findAll();
	} 

	@Override
	public String createNew(Acao acao) {
		return this.acoesRepository.save(acao).getId();
	}
	
	

}
