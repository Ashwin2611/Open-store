package Repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import Model.UsersModel;

@Repository
public interface UserRepo extends MongoRepository<UsersModel, String>{

}
