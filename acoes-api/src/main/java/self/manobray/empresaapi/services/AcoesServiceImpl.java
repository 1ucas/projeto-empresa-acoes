package self.manobray.empresaapi.services;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import self.manobray.empresaapi.domain.Acao;
import self.manobray.empresaapi.domain.InputCreateAcoes;
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
	public String createNew(InputCreateAcoes input) {
		Acao acaoToCreate = new Acao(null, input.getEmpresaId(), input.getEmpresaId(), new Date(), new Date(), input.getInitialValue(), input.getInitialValue());
		return this.acoesRepository.save(acaoToCreate).getId();
	}
	
	

}
