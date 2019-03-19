package self.manobray.empresaapi.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import self.manobray.empresaapi.domain.Acao;

@Repository
public interface AcoesRepository extends MongoRepository<Acao, String>{
	
	List<Acao> findAll();
	
}
