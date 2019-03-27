package self.manobray.empresaapi.services;

import java.util.List;

import self.manobray.empresaapi.domain.Acao;
import self.manobray.empresaapi.domain.InputCreateAcoes;

public interface AcoesService {
	
	Acao getById(String id);
	
	List<Acao> getAll();

	public String createNew(InputCreateAcoes input);

}
