package self.manobray.rabbitmq.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import self.manobray.rabbitmq.domain.Ordem;

@Repository
public interface OrdersRepository extends MongoRepository<Ordem, String>{
	
	List<Ordem> findByOrderType(int orderType);
}
