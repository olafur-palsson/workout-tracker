/**
 * Author: Olafur Palsson
 * HImail: olp6@gmail.com
 * Actual: olafur.palsson
 * Heiti verkefnis: server.user
 */

package trackerApi.generator.user;

import trackerApi.generator.routine.Routine;

import java.util.HashMap;
import java.util.Map;

public class User extends UserEntity {
	private Map<Long, Routine> routines = new HashMap<>();

	public User(Long id, String name, String email, Map<Integer, Long> routines) { super(id, name, email, routines); }
	public User(String name, String email)                                       { super(name, email);               }

	public void setRoutines(Map<Long, Routine> routines) {
		this.routines = routines;
	}
}
