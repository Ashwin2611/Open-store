package com.example.onlineStore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = {"com.example.onlineStore", "Controller","Services","Model","Repo"})
@EnableMongoRepositories(basePackages = {"com.example.onlineStore","Repo"})
public class OnlineStoreApplication {
	public static void main(String[] args) {
		SpringApplication.run(OnlineStoreApplication.class, args);
	}

}
class Store{
	private long  productId;
	private String productName;
	private int productPrice;
	private String ownerName;
	private long ownerPh;
	public void getProductId(long productId) {
		this.productId=productId;
	}
	public long setProductId() {
		return productId;
	}
	public void setProductName(String productName) {
		this.productName=productName;
	}
	public String getProductName() {
		return productName;
	}
	
	public void setProductPrice(int productPrice) {
		this.productPrice=productPrice;
	}
	public int getProductPrice() {
		return productPrice;
	} 
	
	public void setOwnerName(String ownerName) {
		this.ownerName=ownerName;
	}
	public String getOwnerName() {
		return ownerName;
	}
	
	public void setOwnerPh(long ownerPh) {
		this.ownerPh=ownerPh;
	}
	public long getOwnerPh() {
		return ownerPh;
	}
	
}

