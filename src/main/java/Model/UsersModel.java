package Model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
//import javax.persistence.Entity;
import com.example.onlineStore.MyEntity;

//@Entity
@Document(collection="Users")
public class UsersModel {
	private long userId;
	private String name;
	private String email;
	private String password;
	private String gender;
	private long number;
	private String role;
	
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
//	public List<MyEntity> getMyCart() {
//		return myCart;
//	}
//	public void setMyCart(List<MyEntity> myCart) {
//		this.myCart = myCart;
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public long getNumber() {
		return number;
	}
	public void setNumber(long number) {
		this.number = number;
	}

	
}
