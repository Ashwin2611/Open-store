package com.example.onlineStore;
import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController{
	
    @Autowired
    private MyEntityService myEntityService;
	
	@GetMapping
	public ResponseEntity<List<MyEntity>> getAllProducts() {
		List<MyEntity> products=myEntityService.getAllEntities();
		if(products.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		System.out.println("return");
		return ResponseEntity.ok(products);
	}
	
    @PostMapping("/addproduct")
    public ResponseEntity<MyEntity> createProduct(@RequestBody MyEntity product) {
    	List<MyEntity> products=myEntityService.getAllEntities();
    	long incId=myEntityService.incId(products);
    	product.setProductId(incId+1);
        MyEntity savedProduct = myEntityService.saveEntity(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable long id) {
    	MyEntity product=myEntityService.getProduct(id);
    	if(product==null) {
    		return ResponseEntity.badRequest().body("Message:"+"Invalid ProductId");
    	}
    	return ResponseEntity.ok(product);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable long id,@RequestBody MyEntity product){
    	MyEntity updatedEntity = myEntityService.updatePartialFields(id, product);
    	if(updatedEntity==null) {
    		return ResponseEntity.badRequest().body("Message:"+"Invalid ProductId") ;
    	}
        return ResponseEntity.ok(updatedEntity);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long id){
    	MyEntity deleteEntity=myEntityService.deleteProduct(id);
    	if(deleteEntity==null) {
    		return ResponseEntity.badRequest().body("Message:"+"Invalid ProductId") ;
    	}
    	return ResponseEntity.ok(deleteEntity);
    }
    
    
}