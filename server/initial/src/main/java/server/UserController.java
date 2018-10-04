package server;

import org.springframework.web.bind.annotation.*;

import server.history.HistoryEntity;
import server.history.HistoryRepository;
import server.user.UserEntity;
import server.user.UserRepository;

@RestController    // This means that this class is a Controller
@RequestMapping(path="/database") // This means URL's start with /demo (after Application path)
public class UserController {

    private UserRepository userRepository;
    private HistoryRepository historyRepository;

    public UserController() {
        this.userRepository = MainController.userRepository;
        this.historyRepository = MainController.historyRepository;
    }

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
        HistoryEntity h = new HistoryEntity();
        h = historyRepository.save(h);
        u.setHistoryId(h.getId());
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
        Long historyId = u.getHistoryId();
		userRepository.delete(id);
		historyRepository.delete(historyId);
		return "Deleted";
	}
}
