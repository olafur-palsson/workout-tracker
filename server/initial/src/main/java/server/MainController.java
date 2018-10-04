package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.exercise.ExerciseRepository;
import server.routine.RoutineRepository;
import server.setList.SetListRepository;

@RestController    // This means that this class is a Controller
@RequestMapping(path="/database") // This means URL's start with /demo (after Application path)
public class MainController {

    @Autowired
    public static RoutineRepository routineRepository;

    @Autowired
    public static SetListRepository setListRepository;

    @Autowired
    public static ExerciseRepository exerciseRepository;


}
