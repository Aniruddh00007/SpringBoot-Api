package in.main.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import in.main.beans.User_credentials;

public interface UserDAO_Interf extends JpaRepository<User_credentials, Integer>  {

   
}

