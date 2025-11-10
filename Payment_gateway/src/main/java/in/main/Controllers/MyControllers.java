package in.main.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import in.main.Service.User_Service_interf;
import in.main.beans.User_credentials;

@RestController
@RequestMapping("/Userdata")
@CrossOrigin(origins = "http://localhost:5173")

public class MyControllers {

    @Autowired
    private User_Service_interf userService;

    // ✅ Add new user
    @PostMapping
    public User_credentials addUser(@RequestBody User_credentials user) {
        return userService.addUser_credentials(user);
    }

    // ✅ Get all users
    @GetMapping
    public List<User_credentials> getAllUsers() {
        return userService.getallCredentials();
    }

    // ✅ Get user by ID
    @GetMapping("/{id}")
    public User_credentials getUserById(@PathVariable("id") String userId) {
        return userService.getUser_credentials(userId);
    }

    // ✅ Update user
    @PutMapping("/{id}")
    public User_credentials updateUser(@PathVariable("id") String userId, @RequestBody User_credentials updatedUser) {
        updatedUser.setUserId(userId);
        return userService.updateUser_credentials(updatedUser);
    }

    // ✅ Delete user
    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable("id") String userId) {
        return userService.deleteUser_credentials(userId);
    }
}
