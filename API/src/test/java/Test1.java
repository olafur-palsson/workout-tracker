import trackerApi.generator.exercise.Exercise;
import trackerApi.generator.exercise.ExerciseFactory;

import trackerApi.generator.setList.SetList;
import trackerApi.generator.setList.SetListFactory;

import trackerApi.generator.routine.Routine;
import trackerApi.generator.routine.RoutineFactory;

import trackerApi.generator.history.History;
import trackerApi.generator.history.HistoryFactory;


import trackerApi.generator.user.User;
import trackerApi.generator.user.UserFactory;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

//code for Exercises

public class Test1 {
	private ExerciseFactory<Exercise> exerciseFactory = new ExerciseFactory();
	private SetListFactory<SetList> setListFactory = new SetListFactory();
	private RoutineFactory<Routine> routineFactory = new RoutineFactory();
	private UserFactory<User> userFactory = new UserFactory();
	private HistoryFactory<History> historyFactory = new HistoryFactory();

	private Long exercise1Id;
    private Long exercise2Id;
	private Long setListId1;
    private Long setListId2;
	private Long routineId;
	private Long userId;
	private Long historyId;

	private String description = "This is long heotuaheotunhteoabs ueoa oe aueboa bkabo kebakb eoa keoabkbeoakbeoab koea eoa ueonauheoanuhoaebu kboeabkodagkp,d;keoa";
	// lol, ekki nota thetta med mismunandi weights, tha brotnar testid ekki serverinn

    // SetListdata
    private List<Double> weights = Arrays.asList(50.5, 57.5, -50.5);
    private List<Integer> reps = Arrays.asList(-1, 0, 10000);

    // Routinedata
    private List<Long> exerciseIds = new ArrayList<>();
    private List<Long> setListIds = new ArrayList<>();
    private String name2 = "This is the exercise that you are looking %#}{[& for";

	@Before
	public void setUp() throws IOException {
		System.out.println("-------- Test 1 -------");

		// Make two exercises, hibernate
		Exercise exercise1 = new Exercise();
		exercise1.setDescription(description);
		exercise1.setName("No name");
        Exercise exercise2 = new Exercise();
        exercise2.setDescription("No description");
        exercise2.setName(name2);
        exercise1Id = exerciseFactory.save(exercise1);
        exercise2Id = exerciseFactory.save(exercise2);
        exerciseIds = Arrays.asList(exercise1Id, exercise2Id, exercise1Id);

        // Make a setlist, hibernate
        SetList setList1 = new SetList();
        for(int i = 0; i < weights.size(); i++)
            setList1.addSet(weights.get(i), reps.get(i));
        setListId1 = setListFactory.save(setList1);
        SetList setList2 = new SetList();
        for(int i = 0; i < weights.size(); i++)
            setList2.addSet(weights.get(i), reps.get(i));
        setList2.addSet(100, 5);
        setList2.removeSet(2);
        setListId2 = setListFactory.save(setList2);
        setListIds = Arrays.asList(setListId1, setListId1, setListId2);

        // Make a routine, hibernate
        Routine routine = new Routine();
        for(int i = 0; i < setListIds.size(); i++)
            routine.addExercise(exerciseIds.get(i), setListIds.get(i));
        routineId = routineFactory.save(routine);
	}

	@Test
    public void test0() throws IOException {
        SetList setList = setListFactory.getOneSetList(setListId1);
        Assert.assertEquals(setListId1, setList.getId());
        Assert.assertArrayEquals(weights.toArray(), setList.getListOfWeights().toArray());
        Assert.assertArrayEquals(reps.toArray(), setList.getListOfReps().toArray());
    }

	@Test
	public void test1() throws IOException {
		System.out.println("---------- Starting test --------");

		Exercise exercise1 = exerciseFactory.getOneExercise(exercise1Id);
		Assert.assertEquals(exercise1Id, exercise1.getId());
		Assert.assertEquals("No name", exercise1.getName());
		Assert.assertEquals(description, exercise1.getDescription());

        Exercise exercise2 = exerciseFactory.getOneExercise(exercise2Id);
        Assert.assertEquals(exercise2Id, exercise2.getId());
        Assert.assertEquals(name2, exercise2.getName());
        Assert.assertEquals("No description", exercise2.getDescription());
	}

	@Test
    public void test3() throws IOException {
        Routine routine = routineFactory.getOneRoutine(routineId);
        for(Long loooong : routine.getExerciseIds())
            System.out.println(loooong);
        Assert.assertArrayEquals(exerciseIds.toArray(), routine.getExerciseIds().toArray());
        Assert.assertArrayEquals(setListIds.toArray(), routine.getSetListIds().toArray());

    }

    // yolo life's to short to write teardowns
	@After
	public void tearDown() throws IOException {
		System.out.println("----------------Taking the thing out");
	}
}
