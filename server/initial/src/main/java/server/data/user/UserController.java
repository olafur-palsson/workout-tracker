package server.data.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// import server.data.history.HistoryEntity;
// import server.data.history.HistoryRepository;
import server.data.routine.RoutineEntity;
import server.data.routine.RoutineRepository;
import server.data.user.UserEntity;
import server.data.user.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController    // This means that this class is a Controller
@RequestMapping(path="/database") // This means URL's start with /demo (after Application path)
public class UserController {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private RoutineRepository routineRepository;
  // @Autowired
  // private HistoryRepository historyRepository;

  // We can use this one to check if the user is logged in
  // It usually gives us a 400 ~ 500 error if the user is not logged in
  // The front-end check string-equivalence
  @GetMapping(path = "userEnabled/check")
  public @ResponseBody String checkLoggedIn() {
    return "logged_in";
  }

  // Create a new user, this is the only method permitted to everyone
  @GetMapping(path = {"/newUser", "/userEnabled/newUser"})
  public @ResponseBody String addNewUser(
    @RequestParam String name,
    @RequestParam String email,
    @RequestParam String password
  ) {
    System.out.println("Creating user");
    if(userRepository.findOne(email) != null) {
      System.out.println("Username taken");
      return "Username is already taken";
    }

    UserEntity u = new UserEntity();
    u.setEmail(email);
    u.setName(name);
    u.setPassword(password);
    // HistoryEntity history = new HistoryEntity();
    // Long historyId = historyRepository.save(history).getId();
    // u.setHistoryId(historyId);
    u = userRepository.save(u);
    System.out.println("Successfully save user " + email);
    return u.getEmail();
  }

  // A simple remote getter for tidy front-end
  @CrossOrigin
  @GetMapping(path = {"/getActiveRoutine", "/userEnabled/getActiveRoutine"})
  public @ResponseBody RoutineEntity getActiveRoutine(
    @RequestParam String email
  ) {
    Long routineId = userRepository.findOne(email).getActiveRoutine();
    if (routineId.intValue() < 0) return null;
    return routineRepository.findOne(routineId);
  }

  // A simple remote setter for tidy front-end
  @CrossOrigin
  @GetMapping(path = {"/setActiveRoutine", "/userEnabled/setActiveRoutine"})
  public @ResponseBody String setActiveRoutine(
    @RequestParam String email,
    @RequestParam Long routineId
  ) {
    UserEntity user = userRepository.findOne(email);
    user.setActiveRoutine(routineId);
    if (routineId > 0) user.addTrainingRoutine(routineId);
    userRepository.save(user);
    return "Active routine set to " + routineId;
  }

  @CrossOrigin
  @GetMapping(path = {"/getAllPersonalRoutines", "/userEnabled/getAllPersonalRoutines"})
  public @ResponseBody List<RoutineEntity> getAllPersonalRoutines(
    @RequestParam String email
  ) {
    List<Long> routineIds = userRepository.findOne(email).getPersonalRoutines();
    return routineIds.stream().map(
      id -> routineRepository.findOne(id)
    ).collect(Collectors.toList());
  }

  @CrossOrigin
  @GetMapping(path = {"/oneUser", "/userEnabled/oneUser"})
  public @ResponseBody UserEntity getOneUser(
    @RequestParam String id
  ) {
    return userRepository.findOne(id);
  }

  @CrossOrigin
  @GetMapping(path = {"/getAllTrainingRoutines", "userEnabled/getAllTrainingRoutines"})
  public @ResponseBody List<RoutineEntity> getAllTrainingRoutines(
    @RequestParam String email
  ) {
    List<Long> routineIds = userRepository.findOne(email).getTrainingRoutines();
    return routineIds.stream().map(
      id -> routineRepository.findOne(id)
    ).collect(Collectors.toList());
  }

  /*

  DEPRECATED!

  @CrossOrigin
  @GetMapping(path = "/createHistoryEntry")
  public @ResponseBody String createHistoryEntry(
    @RequestParam Long routineId,
    @RequestParam String email
  ) {
    UserEntity user = userRepository.findOne(email);
    HistoryEntity history = historyRepository.findOne(user.getHistoryId());
    history.addRoutine(routineId);
    historyRepository.save(history);
    return "CreatedHistoryEntry";
  }

  @CrossOrigin
  @GetMapping(path = { "/getHistoryOfUser", "userEnabled/getHistoryOfUser" })
  public @ResponseBody HistoryEntity getHistoryOfUser(
    @RequestParam String email
  ) {
    UserEntity user = userRepository.findOne(email);
    System.out.println(user.getHistoryId());
    return historyRepository.findOne(user.getHistoryId());
  }
  */

  // A service that takes the two ids of user and routine and adds the routine to user's collection of routines
  @CrossOrigin
  @GetMapping(path = {"/addRoutineToUser", "/userEnabled/addRoutineToUser"})
  public @ResponseBody String addRoutineToUser(
    @RequestParam String email,
    @RequestParam Long routineId
  ) {
    UserEntity u = userRepository.findOne(email);
    u.addRoutine(routineId);
    userRepository.save(u);

    return "Success";
  }

  @GetMapping(path = "/allUsers")
  public @ResponseBody Iterable<UserEntity> getAllUsers() {
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
