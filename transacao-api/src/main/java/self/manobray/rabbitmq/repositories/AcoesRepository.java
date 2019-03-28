package self.manobray.rabbitmq.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import self.manobray.rabbitmq.domain.Acao;

@Repository
public interface AcoesRepository extends MongoRepository<Acao, String>{
}
