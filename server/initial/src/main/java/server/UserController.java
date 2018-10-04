package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import server.user.UserEntity;
import server.user.UserRepository;


@RestController    // This means that this class is a Controller
@RequestMapping(path="/database") // This means URL's start with /demo (after Application path)
public class UserController {

	/*********************************
	 *   USER METHODS
	 ********************************/
	@Autowired
	private UserRepository userRepository;

	@CrossOrigin
	@GetMapping(path = "/addUser") // Map ONLY GET Requests
	public @ResponseBody
	String addNewUser(
		 @RequestParam(required = false) Long id,
		 @RequestParam(required = false) ArrayList<Long> bookingIds,
		 @RequestParam(required = false) ArrayList<Long> routineIds,
		 @RequestParam String name,
		 @RequestParam String email
	) {
		UserEntity u = new UserEntity();
		if(id != null) 		  u.setId(id);
		if(bookingIds != null) u.setBookingIds((Map<Integer, Long>) Converter.arrayListToMap(bookingIds));
		if(routineIds != null) u.setRoutineIds((Map<Integer, Long>) Converter.arrayListToMap(bookingIds));
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
	@GetMapping(path = "/addBookingToUser")
	public @ResponseBody
	String addBookingToUser(
		 @RequestParam Long userId,
		 @RequestParam Long bookingId
	) {
		UserEntity user = userRepository.findOne(userId);
		user.addBookingId(bookingId);
		return "Done";
	}

	@CrossOrigin
	@GetMapping(path = "/removeUser")
	public @ResponseBody String removeUser(
		 @RequestParam Long id
	) {
		userRepository.delete(id);
		return "Deleted";
	}
}
