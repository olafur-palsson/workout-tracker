package trackerApi.generator.exercise;

import trackerApi.generator.Factory;
import trackerApi.API;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Olafur Palsson
 * HImail: olp6@gmail.com
 * Actual: olafur.palsson
 * Heiti verkefnis: PACKAGE_NAME
 */

public class ExerciseFactory<Ent extends Exercise> extends Factory {
	public ExerciseFactory() {
		this.updateURL = "addExercise";
		this.getAllURL = "allExercises";
		this.removeURL = "removeExercise";
		this.getOneURL = "oneExercise";
	}

	@Override
	public Exercise generate() {
	    return new Exercise();
	}

	public Exercise generate(double latitude, double width, double longitude, double height) {
		return new Exercise();
	}

	public Long save(Ent ent) throws IOException {
		API.setExerciseListNeedsToBeUpdated(true);
		return super.save(ent);
	}

	public Ent getOneExercise(Long id) throws IOException {
		List<Ent> listOfOne =  super.getOne(id);
		return listOfOne.get(0);
	}

	public Ent jsonToEntity(JSONObject json) {
		Ent ent = (Ent) new Exercise();
		ent.setId(Long.parseLong(json.get("id").toString()));
		ent.setName((String) json.get("name"));
        String text = "";
		try {
            JSONObject description = (JSONObject) json.get("description");
            Map<Integer, String> textMap = getStringMapFromJSON(description);
            for(Map.Entry<Integer, String> e : textMap.entrySet())
                text += e.getValue();
        } catch(ClassCastException e) {
		    text = json.get("description").toString();
        }
		ent.setDescription(text);
		return ent;
	}
}
