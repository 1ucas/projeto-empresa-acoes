package self.manobray.empresaapi.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import self.manobray.empresaapi.domain.Empresa;

@Repository
public interface EmpresaRepository extends MongoRepository<Empresa, String>{
	
	List<Empresa> findAll();
	
}
