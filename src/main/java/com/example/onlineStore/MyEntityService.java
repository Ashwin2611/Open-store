package com.example.onlineStore;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyEntityService {
    @Autowired
    private MyEntityRepository repo;

    public List<MyEntity> getAllEntities() {
        List<MyEntity> entities = repo.findAll();
        System.out.println("Fetched Entities: " + entities.get(0).toString());
        return entities;
    }

    public MyEntity saveEntity(MyEntity myEntity) {
    	System.out.print("DB");
        // Here you can add any business logic if required before saving the entity
        return repo.save(myEntity);  // Save the entity to the database
        
    }
    
    public long incId(List<MyEntity> products){
    	long id=0;
    	for(MyEntity product :products) {
    		id=product.getProductId();
    	}
    	return id;
    }
    
    public MyEntity getProduct(long id) {
    	return repo.findOneById(id);
    }
    
    public MyEntity updatePartialFields(long id,MyEntity product) {
       MyEntity entity = repo.findOneById(id);
       if(entity==null) {
    	   return null;
       }
//       System.out.println(entity.getId());
       System.out.println(entity.getOwnerName());
       System.out.println(entity.getProductPrice());
       System.out.println(product.getOwnerName());
       System.out.println(entity!=null);
       Optional<MyEntity> exentity=repo.findById(entity.getId());
    
        if (exentity.isPresent()) {
        	if(product.getProductName()!=null)
        		entity.setProductName(product.getProductName());
        	 if(product.getProductPrice()!=0)
        		entity.setProductPrice(product.getProductPrice());
        	 if(product.getOwnerName()!=null)
        		entity.setOwnerName(product.getOwnerName());
        	 if(product.getOwnerPh()!=0)
        		entity.setOwnerPh(product.getOwnerPh());
        	 
        	MyEntity saveentity=repo.save(entity);
        	return entity;
        } else {
//            throw new EntityNotFoundException("Entity with ID " + id + " not found");
        	 return null;
        }
    }

	public MyEntity deleteProduct(long id) {
		MyEntity entity=repo.findOneById(id);
		if(entity!=null) {
			MyEntity deleteEntity=repo.findByIdAndDelete(id);
			return deleteEntity;
		}
		return null;
	}
}
