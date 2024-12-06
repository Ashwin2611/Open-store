package com.example.onlineStore;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="Products")
public class MyEntity {
	@Id 
	private String id;
	private long productId;
	private String productName;
	private int productPrice;
	private String ownerName;
	private long ownerPh;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setProductId(long productId) {
		this.productId=productId;
	}
	public long getProductId() {
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
