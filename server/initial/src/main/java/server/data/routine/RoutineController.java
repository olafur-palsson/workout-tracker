package server.data.routine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import server.data.setList.SetListEntity;
import server.data.setList.SetListRepository;

@RestController    // This means that this class is a Controller
@RequestMapping(path="/database") // This means URL's start with /demo (after Application path)
public class RoutineController {

    @Autowired
    private RoutineRepository routineRepository;
    @Autowired
    private SetListRepository setListRepository;

	@CrossOrigin
	@GetMapping(path = {"/allRoutines", "/userEnabled/allRoutines"})
	public @ResponseBody
	Iterable<RoutineEntity> getAllRoutines() {
		return routineRepository.findAll();
	}

	@CrossOrigin
	@GetMapping(path = {"/oneRoutine", "/userEnabled/oneRoutine"})
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

    public Long copySetList(Long idOfOriginal) {
        SetListEntity original = setListRepository.findOne(idOfOriginal);
        SetListEntity copy = new SetListEntity();
        copy.setFinishedSets(new ArrayList<>(original.getFinishedSets()));
        copy.setListOfReps(new ArrayList<>(original.getListOfReps()));
        copy.setListOfWeights(new ArrayList<>(original.getListOfWeights()));
        return setListRepository.save(copy).getId();
    }

    @CrossOrigin
    @GetMapping(path = {"/deepCopyRoutine", "/userEnabled/deepCopyRoutine"})
    public @ResponseBody
    RoutineEntity copyRoutine(
            @RequestParam Long id
    ) {
	    RoutineEntity original = routineRepository.findOne(id);
	    RoutineEntity copy = new RoutineEntity();
	    copy.setExerciseIds(new ArrayList<>(original.getExerciseIds()));
	    copy.setName(original.getName());
	    copy.setSetListIds(original.getSetListIds());
	    copy.setCreationDate(new Date().getTime());
	    List<Long> originalSetListIds = original.getSetListIds();
	    List<Long> newSetListIds = new ArrayList<>();
	    for (Long setListId : originalSetListIds)
	        newSetListIds.add(setListId);
        copy.setSetListIds(newSetListIds);
	    return routineRepository.save(copy);
    }

	@CrossOrigin
	@GetMapping(path = {"/removeRoutine", "/userEnabled/removeRoutine"})
	public @ResponseBody
    String removeRoutine(
		 @RequestParam Long id
	) {
	    RoutineEntity routine = routineRepository.findOne(id);
	    removeData(id);
		routineRepository.delete(id);
		return "Deleted";
	}

	@CrossOrigin
	@GetMapping(path = {"/addRoutine", "/userEnabled/addRoutine"})
	public @ResponseBody
	Long addRoutine(
		 @RequestParam (required = false) Long id,
		 @RequestParam (required = false) String name,
		 @RequestParam (required = false) ArrayList<Long> exerciseIds,
         @RequestParam (required = false) ArrayList<Long> setListIds
	) {
		RoutineEntity routine = new RoutineEntity();
		if(id != null) routine.setId(id);
		if(name != null) routine.setName(name);
		else routine.setName("Unnamed routine");
		routine.setExerciseIds(exerciseIds);
		routine.setSetListIds(setListIds);
		routine = routineRepository.save(routine);
		return routine.getId();
	}
}
