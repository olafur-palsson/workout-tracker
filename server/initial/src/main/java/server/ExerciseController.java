package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import server.exercise.ExerciseEntity;
import server.exercise.ExerciseRepository;



@RestController    // This means that this class is a Controller
@RequestMapping(path="/database") // This means URL's start with /demo (after Application path)
public class ExerciseController {

	private ExerciseRepository exerciseRepository;

	public ExerciseController() {
	    this.exerciseRepository = MainController.exerciseRepository;
    }

	@CrossOrigin
	@GetMapping(path = "/addExercise")
	public @ResponseBody
	String addNewExercise(
		 @RequestParam(required = false) Long id,
         @RequestParam String name,
         @RequestParam(required = false) String description
	) {
		ExerciseEntity ee = new ExerciseEntity(name);
		if(id != null) ee.setId(id);
		if(description != null) ee.setDescription(description);
		ee = exerciseRepository.save(ee);
		return "" + ee.getId();
	}

	@CrossOrigin
	@GetMapping(path = "/oneExercise")
	public @ResponseBody
	ExerciseEntity getOneExercise(
		@RequestParam Long id
	) {
		return exerciseRepository.findOne(id);
	}

	@CrossOrigin
	@GetMapping(path = "/removeExercise")
	public @ResponseBody String removeExercise(
		 @RequestParam Long id
	) {
		exerciseRepository.delete(id);
		return "Deleted";
	}
}
