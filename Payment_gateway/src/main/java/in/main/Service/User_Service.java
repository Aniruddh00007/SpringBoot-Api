package in.main.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.main.DAO.UserDAO_Interf;
import in.main.beans.User_credentials;
@Service
public class User_Service implements User_Service_interf {

	@Autowired
	UserDAO_Interf userdao;
	
	@Override
	public User_credentials addUser_credentials(User_credentials adduc) {
		User_credentials usercr =userdao.save(adduc);
		
		return usercr;
	}

	@Override
	public boolean deleteUser_credentials(int userId) {
		 boolean status= false;
		try {
			  userdao.deleteById(userId);
			  status =true;
		} catch (Exception e) {
			status = false;
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public User_credentials updateUser_credentials(User_credentials upduc) {
		  User_credentials uscr = userdao.save(upduc);
		return uscr;
	}

	@Override
	public User_credentials getUser_credentials(int userId) {
		Optional<User_credentials> optional =userdao.findById(userId);
		return optional.get();
	}

	@Override
	public List<User_credentials> getallCredentials() {
		 return userdao.findAll();
	}

}
