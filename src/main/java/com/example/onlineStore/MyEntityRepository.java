package com.example.onlineStore;

import java.util.Map;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MyEntityRepository extends MongoRepository<MyEntity, String>{
	@Query("{ 'productId': ?0 }")
	MyEntity findOneById(long id);
	
	Optional<MyEntity> findById(String id);

//    @Modifying
//    @Query(value = "{ '_id': ?0 }", update = "{ '$set': ?1 }")
//    void updateFieldsById(long id, Map<String, Object> fields);
    @Query(value = "{ 'productId': ?0 }", delete = true)
    MyEntity findByIdAndDelete(long id);
}
