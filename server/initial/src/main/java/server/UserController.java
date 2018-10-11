package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import server.history.HistoryEntity;
import server.history.HistoryRepository;
import server.user.UserEntity;
import server.user.UserRepository;

@RestController    // This means that this class is a Controller
@RequestMapping(path="/database") // This means URL's start with /demo (after Application path)
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HistoryRepository historyRepository;


    @CrossOrigin
    @GetMapping(path = "/addUser") // Map ONLY GET Requests
    public @ResponseBody
    String addNewUser(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String email
    ) {
        UserEntity u = new UserEntity();
        if(id != null) 		  u.setId(id);
        u.setName(name);
        u.setEmail(email);
        u = userRepository.save(u);
        return u.getId().toString();
    }

	@CrossOrigin
	@GetMapping(path = "/oneUser")
	public @ResponseBody
	UserEntity getOneUser(
		 @RequestParam Long id
	) {
		return userRepository.findOne(id);
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
		 @RequestParam Long id
	) {
        UserEntity u = userRepository.findOne(id);
		userRepository.delete(id);
		return "Deleted";
	}
}
