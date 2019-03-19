package self.manobray.empresaapi.services;

import java.util.List;

import self.manobray.empresaapi.domain.Acao;

public interface AcoesService {
	
	Acao getById(String id);
	
	List<Acao> getAll();

	String createNew(Acao acao);

}
