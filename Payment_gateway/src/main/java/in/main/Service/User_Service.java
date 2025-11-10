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
    private UserDAO_Interf userdao;

    @Override
    public User_credentials addUser_credentials(User_credentials adduc) {
        return userdao.save(adduc);
    }

    @Override
    public boolean deleteUser_credentials(String userId) {
        try {
            userdao.deleteById(userId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User_credentials updateUser_credentials(User_credentials upduc) {
        return userdao.save(upduc);
    }

    @Override
    public User_credentials getUser_credentials(String userId) {
        Optional<User_credentials> optional = userdao.findById(userId);
        return optional.orElse(null);
    }

    @Override
    public List<User_credentials> getallCredentials() {
        return userdao.findAll();
    }
}
