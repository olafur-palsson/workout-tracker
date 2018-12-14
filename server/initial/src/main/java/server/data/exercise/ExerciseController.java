package server.data.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import server.data.exercise.ExerciseEntity;
import server.data.exercise.ExerciseRepository;

import java.util.ArrayList;


@RestController    // This means that this class is a Controller
@RequestMapping(path="/database") // This means URL's start with /demo (after Application path)
public class ExerciseController {

  @Autowired
  private ExerciseRepository exerciseRepository;

  @CrossOrigin
  @GetMapping(path = {"/addExercise", "/userEnabled/addExercise"})
  public @ResponseBody String addNewExercise(
  @RequestParam(required = false) Long id,
  @RequestParam String name,
  @RequestParam(required = false) ArrayList<String> description
  ) {
    ExerciseEntity ee = new ExerciseEntity(name);
    if(id != null) ee.setId(id);
    if(description != null) ee.setDescription(description);
    ee = exerciseRepository.save(ee);
    return "" + ee.getId();
  }

  @CrossOrigin
  @GetMapping(path = {"/oneExercise", "/userEnabled/oneExercise"})
  public @ResponseBody ExerciseEntity getOneExercise(
  @RequestParam Long id
  ) {
    return exerciseRepository.findOne(id);
  }

  @CrossOrigin
  @GetMapping(path = {"/allExercises", "/userEnabled/allExercises"})
  public @ResponseBody Iterable<ExerciseEntity> getOneExercise( ) {
    return exerciseRepository.findAll();
  }

  @CrossOrigin
  @GetMapping(path = {"/removeExercise", "/userEnabled/removeExercise"})
  public @ResponseBody String removeExercise(
  @RequestParam Long id
  ) {
    exerciseRepository.delete(id);
    return "Deleted";
  }
}






u
