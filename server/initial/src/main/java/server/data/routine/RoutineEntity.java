package server.data.routine;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/*

This class has the role of making a routine (think of an hour lifting in the gym).
It has a list of exercises for each exercise a corresponding list of sets.

For example, if we expand the Routine into it's components we have
RoutineId --Ref--> ExerciseIds and SetListIds
ExerciseId --Ref--> A flat object with name of the exercise, description etc.
SetListId --Ref--> List of reps and list of weights

A routine with one pair of exerciseId and setListId can look like this:
Benchpress:
5x7 50kg
or
Benchpress:
5 50kg - unfinished,
3 40kg - done and
12 666kg - done

*/
@Entity // This tells Hibernate to make a table out of this class
public class RoutineEntity {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  private Long timeStamp;
  private String name;

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

  public Long getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(Long timeStamp) {
    this.timeStamp = timeStamp;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
