package Services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Model.UsersModel;
import Repo.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo repo;

	public List<UsersModel> getAllUsers(){
		List<UsersModel> users=repo.findAll();
		return users;
	}
	public UsersModel addUser(UsersModel user) {
		return repo.save(user);
	}

	public long getUserId() {
		// TODO Auto-generated method stub
		long inc=0;
		List<UsersModel> list=getAllUsers();
		for(UsersModel user:list) {
			inc=user.getUserId();
		}
		return inc;
	}
	public String validateUser(UsersModel us) {
		// TODO Auto-generated method stub
		List<UsersModel> list=getAllUsers();
		if(list.isEmpty()) {
			return "noData";
		}
		for(UsersModel user:list) {
			if(user.getEmail().equals(us.getEmail())) {
				return "Invalid";
			}
		}
		return "valid";
	}
	
}
