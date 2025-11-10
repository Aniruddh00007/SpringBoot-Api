package in.main.Service;

import java.util.List;
import in.main.beans.User_credentials;

public interface User_Service_interf {

    public User_credentials addUser_credentials(User_credentials adduc);

    public boolean deleteUser_credentials(String userId);

    public User_credentials updateUser_credentials(User_credentials upduc);

    public User_credentials getUser_credentials(String userId);

    public List<User_credentials> getallCredentials();
}
