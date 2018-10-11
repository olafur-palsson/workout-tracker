/**
 * Author: Olafur Palsson
 * HImail: olp6@gmail.com
 * Actual: olafur.palsson
 * Heiti verkefnis: server
 */

package trackerApi.generator.routine;
import trackerApi.generator.Entity;

import javafx.util.Pair;
import java.util.ArrayList;
import java.util.List;


public class Routine extends Entity {
    private List<Long> exerciseIds = new ArrayList<>();
    private List<Long> setListIds = new ArrayList<>();

	@Override
	public List<Pair<String, String>> getParameters() {
		List<Pair<String, String>> params = new ArrayList<>();
		if(this.id != null)
			params.add(new Pair<>("id", "" + this.id));
		for(Long exerciseId : exerciseIds)
		    params.add(pair("exerciseIds", exerciseId));
        for(Long setListId : setListIds)
            params.add(pair("setListIds", setListId));
		return params;
	}

	public void addExercise(Long exerciseId, Long setListId) {
	    exerciseIds.add(exerciseId);
	    setListIds.add(setListId);
    }

    public void removeExercise(int index) throws ArrayIndexOutOfBoundsException {
	    exerciseIds.remove(index);
	    setListIds.remove(index);
    }

	public List<Long> getExerciseIds() {
		return exerciseIds;
	}
	public void setExerciseIds(List<Long> exerciseIds) {
		this.exerciseIds = exerciseIds;
	}

	public List<Long> getSetListIds() {
	    return setListIds;
    }
    public void setSetListIds(List<Long> setListIds) {
	    this.setListIds = setListIds;
    }
}
