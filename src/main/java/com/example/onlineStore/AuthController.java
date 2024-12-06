package com.example.onlineStore;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Model.UsersModel;
import Services.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private UserService service;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) {
        try {
        	List<UsersModel> list=service.getAllUsers();
        	System.out.println(list);
        	for(UsersModel user:list) {
//        		System.out.println(user.getEmail()+authRequest.getEmail());
//        		System.out.println(user.getPassword()+authRequest.getPassword());
        		if (user.getEmail().equals(authRequest.getEmail()) && user.getPassword().equals(authRequest.getPassword())) {
        			String token = jwtUtil.generateToken(authRequest.getEmail());
        			System.out.println(token);
        			return ResponseEntity.ok(new AuthResponse(token,user.getRole())); // Return token wrapped in response
        		} 	
        	}
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");	
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Authentication Failed");
        }
    }
}
 class AuthResponse {
    private String token;
    private String role;
    public AuthResponse(String token,String role){
    	this.token=token;
    	this.role=role;
    }

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
class AuthRequest {
    private String email;
    private String password;


	public String getEmail() {
		return email;
	}

	public void setemail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    // Getters and Setters
}
