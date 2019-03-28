package self.manobray.rabbitmq.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import self.manobray.rabbitmq.domain.Cliente;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String>{
	
}
