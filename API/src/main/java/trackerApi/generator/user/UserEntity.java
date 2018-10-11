package trackerApi.generator.user;

import trackerApi.generator.Entity;
import trackerApi.generator.routine.Routine;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserEntity extends Entity {

	private Long id;
	private String name;
	private String email;
	//routineNumber and routineId
	private Map<Integer, Long> routines = new HashMap<>();

	public UserEntity(Long id, String name, String email, Map<Integer, Long> routines) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.routines = routines;
	}

	public UserEntity(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public boolean hasRoutine(Routine b) {
		if(b.getId() == null) throw new IllegalArgumentException("Routine doesn't have an ID");
		Map<Integer, Long> routines = getRoutines();
		for(int i = 0; i < routines.size(); i++) {
			if(b.getId().equals(routines.get(i)))
				return true;
		}
		return false;
	}

	public UserEntity extractEntity() {
		return new UserEntity(id, name, email, routines);
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public List<Pair<String, String>> getParameters() {
		List<Pair<String, String>> params = new ArrayList<>();
		if(this.id != null)
			params.add(new Pair<>("id", "" + this.id));
		params.add(new Pair<>("name", "" + this.name));
		params.add(new Pair<>("email", "" + this.email));
		if(routines.size() != 0) {
			params.addAll(mapToListOfPairs("routineIds", routines));
		}
		return params;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Map<Integer, Long> getRoutines() {
		return routines;
	}

	public void addRoutineId(Long routineId) {
		int routineNumber = routines.size();
		this.routines.put(routineNumber, routineId);
	}
}
