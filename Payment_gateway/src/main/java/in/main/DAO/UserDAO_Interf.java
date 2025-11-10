package in.main.DAO;

import org.springframework.data.mongodb.repository.MongoRepository;
import in.main.beans.User_credentials;

public interface UserDAO_Interf extends MongoRepository<User_credentials, String> {
}
