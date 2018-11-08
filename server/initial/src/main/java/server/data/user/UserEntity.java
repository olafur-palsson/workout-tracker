package server.data.user;

import server.data.history.HistoryEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // This tells Hibernate to make a table out of this class
public class UserEntity {

  @Id
  private String email;
  private String name;
  private String password;
  private Long historyId;
  @ElementCollection
  private List<Long> personalRoutines = new ArrayList<>();

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

  public Long getHistoryId() {
    return historyId;
  }

  public void setHistoryId(Long historyId) {
    this.historyId = historyId;
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
}
