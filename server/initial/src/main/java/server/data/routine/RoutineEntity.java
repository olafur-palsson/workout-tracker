package server.data.routine;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // This tells Hibernate to make a table out of this class
public class RoutineEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@ElementCollection
	private List<Long> exerciseIds = new ArrayList<>();
	@ElementCollection
    private List<Long> setListIds = new ArrayList<>();

    public void addExercise(long exerciseId, long setListId) {
        exerciseIds.add(exerciseId);
        setListIds.add(setListId);
    }

    public void removeExercise(int index) throws ArrayIndexOutOfBoundsException {
        exerciseIds.remove(index);
        setListIds.remove(index);
    }

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public List<Long> getSetListIds() { return setListIds; }
    public void setSetListIds(List<Long> setListIds) { this.setListIds = setListIds; }

    public List<Long> getExerciseIds() { return exerciseIds; }
    public void setExerciseIds(List<Long> exerciseIds) { this.exerciseIds = exerciseIds; }
}
