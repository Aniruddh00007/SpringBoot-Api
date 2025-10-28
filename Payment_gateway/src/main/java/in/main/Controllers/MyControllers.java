package in.main.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import in.main.Service.User_Service_interf;
import in.main.beans.User_credentials;

@RestController
@RequestMapping("/Userdata")
public class MyControllers {

    @Autowired
    private User_Service_interf userintf;

    // ✅ Add new user
    @PostMapping
    public User_credentials addUser_credentials(
            @RequestParam("id1") int userId,
            @RequestParam("name1") String name,
            @RequestParam("pass1") String password,
            @RequestParam("Amount1") Long amount,
            @RequestParam("father1") String fatherName,
            @RequestParam("City1") String city,
            @RequestParam("Upi1") int upi) {

        User_credentials user = new User_credentials();
        user.setUserId(userId);
        user.setName(name);
        user.setPassword(password);
        user.setAmount(amount);
        user.setFatherName(fatherName);
        user.setCity(city);
        user.setUPI(upi);

        return userintf.addUser_credentials(user);
    }

  
    @GetMapping
    public List<User_credentials> getAllCredentials() {
        return userintf.getallCredentials();
    }


    @GetMapping("/{id}")
    public User_credentials getUserById(@PathVariable("id") int userId) {
        return userintf.getUser_credentials(userId);
    }


    @PutMapping
    public User_credentials updateUser(@RequestBody User_credentials updatedUser) {
        return userintf.updateUser_credentials(updatedUser);
    }

 
    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable("id") int userId) {
        return userintf.deleteUser_credentials(userId);
    }
}
