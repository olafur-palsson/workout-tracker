package server;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import server.routine.RoutineEntity;
import server.routine.RoutineRepository;
import server.setList.SetListRepository;


@RestController    // This means that this class is a Controller
@RequestMapping(path="/database") // This means URL's start with /demo (after Application path)
public class RoutineController {

    private RoutineRepository routineRepository;
    private SetListRepository setListRepository;

    public RoutineController() {
        this.routineRepository = MainController.routineRepository;
        this.setListRepository = MainController.setListRepository;
    }

	@CrossOrigin
	@GetMapping(path = "/allRoutines")
	public @ResponseBody
	Iterable<RoutineEntity> getAllRoutines() {
		return routineRepository.findAll();
	}

	@CrossOrigin
	@GetMapping(path = "/oneRoutine")
	public @ResponseBody
	RoutineEntity getOneRoutine(
		 @RequestParam Long id
	) {
		return routineRepository.findOne(id);
	}

	private void removeData(Long id) {
        RoutineEntity routine = routineRepository.findOne(id);
        List<Long> setListIds = routine.getSetListIds();
        setListIds.forEach(setListId -> setListRepository.delete(id));
    }

	@CrossOrigin
	@GetMapping(path = "/removeRoutine")
	public @ResponseBody String removeRoutine(
		 @RequestParam Long id
	) {
	    RoutineEntity routine = routineRepository.findOne(id);
	    removeData(id);
		routineRepository.delete(id);
		return "Deleted";
	}

	@CrossOrigin
	@GetMapping(path = "/addRoutine")
	public @ResponseBody
	Long addRoutine(
		 @RequestParam (required = false) Long id,
		 @RequestParam ArrayList<Long> exerciseIds,
         @RequestParam ArrayList<Long> setListIds
	) {
		RoutineEntity routine = new RoutineEntity();
		if(id != null)         routine.setId(id);
		routine.setExerciseIds(exerciseIds);
		routine.setSetListIds(setListIds);
		routine = routineRepository.save(routine);
		return routine.getId();
	}
}
