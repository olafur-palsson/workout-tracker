package server.data.user;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
* A User is a class that dictates what data are relevant to each user.
* A user has a history, he has routines that he has made (or added to his collection)
* He also has at most one active routine which corresponds to a current gym session.
*/
@Entity // This tells Hibernate to make a table out of this class
public class UserEntity {

  @Id
  private String email;
  private String name;
  private String password;
  // 0 means no active routine since -1 is never an ID
  private Long activeRoutine = -1L;
  @ElementCollection
  private List<Long> personalRoutines = new ArrayList<>();
  @ElementCollection
  private List<Long> trainingRoutines = new ArrayList<>();

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

  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }

  public void addRoutine(Long routineId) {
    personalRoutines.add(routineId);
  }

  public List<Long> getPersonalRoutines() {
    return personalRoutines;
  }

  public void setPersonalRoutines(List<Long> personalRoutines) {
    this.personalRoutines = personalRoutines;
  }

  public void addTrainingRoutine(Long routineId) {
    this.trainingRoutines.add(routineId);
  }

  public List<Long> getTrainingRoutines() {
    return trainingRoutines;
  }

  public void setTrainingRoutise(List<Long> trainingRoutines) {
    this.trainingRoutines = trainingRoutines;
  }

  public Long getActiveRoutine() {
    return activeRoutine;
  }

  public void setActiveRoutine(Long activeRoutine) {
    this.activeRoutine = activeRoutine;
  }
}
