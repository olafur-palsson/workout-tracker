/**
 * Author: Olafur Palsson
 * HImail: olp6@gmail.com
 * Actual: olafur.palsson
 * Heiti verkefnis: server
 */

package trackerApi.generator.history;


import trackerApi.generator.Entity;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;



public class History extends Entity {

    private HashMap<Long, Long> routineIds = new HashMap<>();

	public History() {}

	@Override
	public List<Pair<String, String>> getParameters() {
		List<Pair<String, String>> params = new ArrayList<>();
		if(this.id != null)
			params.add(pair("id", "" + id));
		for(Map.Entry<Long, Long> routineId : routineIds.entrySet()) {
            params.add(pair("routineId", routineId.getValue()));
            params.add(pair("timestamp", routineId.getKey()));
        }
		return params;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public HashMap<Long, Long> getRoutineIds() {
	    return routineIds;
    }

    public void setRoutineIds(HashMap<Long, Long> routineIds) {
	    this.routineIds = routineIds;
    }
}
