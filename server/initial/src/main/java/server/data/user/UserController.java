package server.data.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import server.data.history.HistoryRepository;
import server.data.user.UserEntity;
import server.data.user.UserRepository;

@RestController    // This means that this class is a Controller
@RequestMapping(path="/database") // This means URL's start with /demo (after Application path)
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HistoryRepository historyRepository;


    @CrossOrigin
    @GetMapping(path = "/newUser")
    public @ResponseBody
    String addNewUser(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password
    ) {
        UserEntity u = new UserEntity();
        u.setEmail(email);
        u.setName(name);
        u.setPassword(password);
        u = userRepository.save(u);
        return u.getEmail();
    }

    @CrossOrigin
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
    }

	@CrossOrigin
	@GetMapping(path = {"/oneUser", "userAccessible/oneUser"})
	public @ResponseBody
	UserEntity getOneUser(
		 @RequestParam String email
	) {
		return userRepository.findOne(email);
	}

	@CrossOrigin
	@GetMapping(path = "/allUsers")
	public @ResponseBody
	Iterable<UserEntity> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}

	@CrossOrigin
	@GetMapping(path = "/removeUser")
	public @ResponseBody String removeUser(
		 @RequestParam String email
	) {
		userRepository.delete(email);
		return "Deleted";
	}
}