package Repo;
 
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
 
import Model.OrderMode;
import Model.UsersModel;
 
@Repository
public interface OrderRepo extends MongoRepository<OrderMode, String>{
	
}