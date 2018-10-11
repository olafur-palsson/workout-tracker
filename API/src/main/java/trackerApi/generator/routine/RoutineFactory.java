package trackerApi.generator.routine;

import trackerApi.generator.Factory;
import javafx.util.Pair;
import org.json.JSONObject;

import javax.xml.ws.WebServiceProvider;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

/**
 * Author: Olafur Palsson
 * HImail: olp6@gmail.com
 * Actual: olafur.palsson
 * Heiti verkefnis: PACKAGE_NAME
 */

public class RoutineFactory<Ent extends Routine> extends Factory {
	public RoutineFactory() {
		this.updateURL = "addRoutine";
		this.getAllURL = "allRoutines";
		this.getOneURL = "oneRoutine";
		this.removeURL = "removeRoutine";
	}

	public Routine generate() {
		Routine routine = new Routine();
		return routine;
	}

	public Ent getOneRoutine(long id) throws IOException {
		List<Ent> listOfOne = getOne(id);
		return listOfOne.get(0);
	}

	@Deprecated
	public Routine jsonToEntity(JSONObject json) {
	    Routine routine = new Routine();
	    Long id = getAsLong("id", json);
        ArrayList<Long> exerciseIds = getAsArrayListOfLong("exerciseIds", json);
        ArrayList<Long> setListIds = getAsArrayListOfLong("setListIds", json);


        routine.setExerciseIds(exerciseIds);
        routine.setSetListIds(setListIds);
	    routine.setId(id);
	    return routine;
	}
}
