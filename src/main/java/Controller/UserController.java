package Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Model.UsersModel;
import Services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<UsersModel>> getAllUser(){
		System.out.println("getAll");
		List<UsersModel> users=service.getAllUsers();
		if(users.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(users);
	}
	
	@PostMapping("/addUser")
	public ResponseEntity<?> postUser(@RequestBody UsersModel user){
		System.out.println("users");
		String email=service.validateUser(user);
		if(email.equals("valid")) {
			long getuserId=service.getUserId();
			user.setUserId(getuserId+1);
			UsersModel users=service.addUser(user);
			return ResponseEntity.ok(users);	
		}
		else if(email.equals("noData")){
			return ResponseEntity.badRequest().body("No data");
		}else {
			return ResponseEntity.badRequest().body("Message:"+"User Already Exist");
		}
		
	}
}
