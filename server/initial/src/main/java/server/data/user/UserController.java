package server.data.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import server.data.history.HistoryRepository;
import server.data.user.UserEntity;
import server.data.user.UserRepository;

@CrossOrigin
@RestController    // This means that this class is a Controller
@RequestMapping(path="/database") // This means URL's start with /demo (after Application path)
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HistoryRepository historyRepository;

    // We can use this one to check if the user is logged in
    @GetMapping(path = "userEnabled/check")
    public @ResponseBody
    String checkLoggedIn() {
        return "logged_in";
    }

    @GetMapping(path = {"/newUser", "userEnabled/newUser"})
    public @ResponseBody
    String addNewUser(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password
    ) {
        if(userRepository.findOne(email) != null)
            return "Username is already taken";
        UserEntity u = new UserEntity();
        u.setEmail(email);
        u.setName(name);
        u.setPassword(password);
        u = userRepository.save(u);
        return u.getEmail();
    }

    /*
    @GetMapping(path = "/addUser") // Map ONLY GET Requests
    public @ResponseBody
    String addNewUser(
            @RequestParam String name,
            @RequestParam String email
    ) {
        UserEntity u = new UserEntity();
        u.setName(name);
        u.setEmail(email);
        u = userRepository.save(u);
        return u.getEmail();
    }*/

    @CrossOrigin
	@GetMapping(path = {"/oneUser", "userEnabled/oneUser"})
	public @ResponseBody
	UserEntity getOneUser(
		 @RequestParam String id
	) {
		return userRepository.findOne(id);
	}

	@GetMapping(path = "/allUsers")
	public @ResponseBody
	Iterable<UserEntity> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}

	@GetMapping(path = "/removeUser")
	public @ResponseBody String removeUser(
		 @RequestParam String email
	) {
		userRepository.delete(email);
		return "Deleted";
	}
}
