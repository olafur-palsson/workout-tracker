
package trackerApi;

import trackerApi.generator.Factory;
import trackerApi.generator.setList.SetList;
import trackerApi.generator.setList.SetListFactory;
import trackerApi.generator.history.History;
import trackerApi.generator.history.HistoryFactory;
import trackerApi.generator.routine.Routine;
import trackerApi.generator.routine.RoutineFactory;
import trackerApi.generator.exercise.Exercise;
import trackerApi.generator.exercise.ExerciseFactory;
import trackerApi.generator.user.User;
import trackerApi.generator.user.UserEntity;
import trackerApi.generator.user.UserFactory;
import trackerApi.generator.ToolBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class API {

	public final ExerciseFactory<Exercise> exercise = new ExerciseFactory<>();
	public final UserFactory<UserEntity> user = new UserFactory<>();
	public final RoutineFactory<Routine> routine = new RoutineFactory<>();
	public final SetListFactory<SetList> setList = new SetListFactory<>();
	public final HistoryFactory<History> history = new HistoryFactory<>();

	private static List<Exercise> allExercises = new ArrayList<>();
	private static boolean exerciseListNeedsToBeUpdated = false;

	public static void setExerciseListNeedsToBeUpdated(boolean b) {
		exerciseListNeedsToBeUpdated = b;
	}

	public List getAllExercises() throws IOException {
		if(!exerciseListNeedsToBeUpdated) return allExercises;
		allExercises = exercise.getAll();
		exerciseListNeedsToBeUpdated = false;
		return allExercises;
	}

	public Exercise getOneExercise(Long id) throws IOException {
		List<Exercise> listWithOnlyOneItem = exercise.getOne(id);
		return listWithOnlyOneItem.get(0);
	}

	public Long makeRoutine(
		Long   exerciseId,
		Long roomId,
		Long userId,
		String dateFrom,
		String dateTo,
		Boolean isPaid,
		Boolean isCancelled,
		String cc
	) throws IOException {
		long from = ToolBox.formatStringDateToLong(dateFrom);
		long to   = ToolBox.formatStringDateToLong(dateTo);
		Routine b = new Routine();
		Long id = routine.save(b);
		return id;
	}


	public void generateExercises(int numberOfExercises) throws IOException {
	    System.out.println("Nope, nothing here");
	}

	public void giveUserRandomRoutines(User user) throws IOException {
		int n = Factory.randomInt(8) + 1;
		for(int i = 0; i < n; i++) {
			user.addRoutineId(routine.save(routine.generate()));
			System.out.println("Creating Routine... " + i);
		}
	}

	public void generateUsers(int numberOfUsers) throws IOException {
		for(int i = 0; i < numberOfUsers; i++) {
			User u = user.generate();
			giveUserRandomRoutines(u);
			System.out.println("Creating User... " + i);
			user.save(u);
		}
	}

	public void generateHistorys(int numberOfHistorys) throws IOException {
		for(int i = 0; i < numberOfHistorys; i++) {
			System.out.println("Generating history " + i);
			History r = history.generate();
			history.save(r);
		}
	}

	public static void main(String [] args) throws IOException {
		API api = new API();
		api.generateHistorys(1500);
		api.generateExercises(1500);
		api.generateUsers(500);


		//daemi hvernig madur notar
		API api2 = new API();
		Exercise h = api.exercise.generate();
		Long exerciseId = api.exercise.save(h);
		System.out.println(api.exercise.getOne(exerciseId));
		List<Exercise> list = api.exercise.getOne(exerciseId);
		h = list.get(0);
		Exercise h2 = h;
		SetList a = api.setList.generate();
		// ... so on
		//
	}
}
