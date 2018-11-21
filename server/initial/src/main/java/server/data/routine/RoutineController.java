package server.data.routine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import server.data.exercise.ExerciseEntity;
import server.data.exercise.ExerciseRepository;
import server.data.setList.SetListEntity;
import server.data.setList.SetListRepository;

/**
* History is nothing but a list of pairs where each pair is
* a tuple of (time, routine)
*/

@RestController    // This means that this class is a Controller
@RequestMapping(path="/database") // This means URL's start with /demo (after Application path)
public class RoutineController {

  @Autowired
  private RoutineRepository routineRepository;
  @Autowired
  private SetListRepository setListRepository;
  @Autowired
  private ExerciseRepository exerciseRepository;

  @CrossOrigin
  @GetMapping(path = {"/allRoutines", "/userEnabled/allRoutines"})
  public @ResponseBody Iterable<RoutineEntity> getAllRoutines() {
    return routineRepository.findAll();
  }

  @CrossOrigin
  @GetMapping(path = {"/getAllExercisesOfRoutine", "/userEnabled/getAllExercisesOfRoutine"})
  public @ResponseBody List<ExerciseEntity> allExercisesOfRoutine(
    @RequestParam Long routineId
  ) {
    RoutineEntity routine = routineRepository.findOne(routineId);
    List<Long> exerciseIds = routine.getExerciseIds();
    return exerciseIds.stream().map(
    id -> exerciseRepository.findOne(id)
    ).collect(Collectors.toList());
  }

  @CrossOrigin
  @GetMapping(path = {"/getAllSetListsOfRoutine", "/userEnabled/getAllSetListsOfRoutine"})
  public @ResponseBody List<SetListEntity> allSetListsOfRoutine(
    @RequestParam Long routineId
  ) {
    List<Long> setListIds = routineRepository.findOne(routineId).getSetListIds();
    return setListIds.stream().map(
    id -> setListRepository.findOne(id)
    ).collect(Collectors.toList());
  }

  @CrossOrigin
  @GetMapping(path = {"/removeExerciseFromRoutine", "/userEnabled/removeExerciseFromRoutine"})
  public @ResponseBody String removeExerciseFromRoutine(
  @RequestParam Long routineId,
  @RequestParam Integer index
  ) {
    RoutineEntity routine = routineRepository.findOne(routineId);
    Long setListId = routine.getSetListIds().get(index);
    routine.removeExercise(index);
    setListRepository.delete(setListId);
    routineRepository.save(routine);
    return "Successfully removed exercise at index " + index;
  }

  @CrossOrigin
  @GetMapping(path = {"/markAllSetsAsDone", "/userEnabled/markAllSetsAsDone"})
  public @ResponseBody String markAllSetsAsDone(
  @RequestParam Long routineId
  ) {
    RoutineEntity routine = routineRepository.findOne(routineId);
    List<Long> setListIds = routine.getSetListIds();
    setListIds.forEach(
    id -> {
      SetListEntity setList = setListRepository.findOne(id);
      List<Boolean> finishedSets = setList.getFinishedSets().stream().map(setStatus -> true).collect(Collectors.toList());
      setList.setFinishedSets(finishedSets);
      setListRepository.save(setList);
    });
    return "Marked all as done for setList " + routineId;
  }

  @CrossOrigin
  @GetMapping(path = {"/oneRoutine", "/userEnabled/oneRoutine"})
  public @ResponseBody RoutineEntity getOneRoutine(
    @RequestParam Long id
  ) {
    return routineRepository.findOne(id);
  }

  // Function to remove all SetLists from database when the Routine is deleted
  private void removeData(Long id) {
    RoutineEntity routine = routineRepository.findOne(id);
    List<Long> setListIds = routine.getSetListIds();
    setListIds.forEach(setListId -> setListRepository.delete(id));
  }

  // Makes a new setList from the id of the original, returns id of the copy
  public Long copySetList(Long idOfOriginal) {
    SetListEntity original = setListRepository.findOne(idOfOriginal);
    SetListEntity copy = new SetListEntity();
    // Get and set data manually
    copy.setFinishedSets(new ArrayList<>(original.getFinishedSets()));
    copy.setListOfReps(new ArrayList<>(original.getListOfReps()));
    copy.setListOfWeights(new ArrayList<>(original.getListOfWeights()));
    // Save and return the id of the copy
    return setListRepository.save(copy).getId();
  }

  @CrossOrigin
  @GetMapping(path = {"/deepCopyRoutine", "/userEnabled/deepCopyRoutine"})
  public @ResponseBody RoutineEntity copyRoutine(
    @RequestParam Long id
  ) {
    RoutineEntity original = routineRepository.findOne(id);
    RoutineEntity copy = new RoutineEntity();

    // FIrst get data from original and put on the copy
    copy.setName(original.getName());
    copy.setExerciseIds(new ArrayList<>(original.getExerciseIds()));
    copy.setSetListIds(original.getSetListIds());
    copy.setTimestamp(new Date().getTime());
    // Take all the SetLists, copy the setLists, and put the id into newSetListIds
    List<Long> originalSetListIds = original.getSetListIds();
    List<Long> newSetListIds = new ArrayList<>();
    for (Long setListId : originalSetListIds)
    newSetListIds.add(copySetList(setListId));
    copy.setSetListIds(newSetListIds);
    return routineRepository.save(copy);
  }

  @CrossOrigin
  @GetMapping(path = {"/removeRoutine", "/userEnabled/removeRoutine"})
  public @ResponseBody String removeRoutine(
    @RequestParam Long id
  ) {
    removeData(id);
    routineRepository.delete(id);
    return "Deleted";
  }

  @CrossOrigin
  @GetMapping(path = {"/addRoutine", "/userEnabled/addRoutine"})
  public @ResponseBody Long addRoutine(
    @RequestParam (required = false) Long id,
    @RequestParam (required = false) String name,
    @RequestParam (required = false) ArrayList<Long> exerciseIds,
    @RequestParam (required = false) ArrayList<Long> setListIds,
    @RequestParam (required = false) Long timestamp
  ) {
    RoutineEntity routine = new RoutineEntity();
    if(id != null) routine.setId(id);
    else if(timestamp != null) routine.setTimestamp(timestamp);
    else routine.setTimestamp(System.currentTimeMillis());

    if(name != null) routine.setName(name);
    else             routine.setName("Not named");


    routine.setExerciseIds(exerciseIds);
    routine.setSetListIds(setListIds);
    routine = routineRepository.save(routine);
    return routine.getId();
  }
}
