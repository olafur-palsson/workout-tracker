
package server.data.history;

import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

/**

IMPORTANT:

Since Routine got a timestamp now and most people don't exercise more 20 times
per day then this class is essentially useless.

TODO: Refactor this bullshit

**/

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class HistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // <date as long, routineId
    private List<Long> routineIds = new ArrayList<>();
    private List<Long> timeStamps = new ArrayList<>();

    // Should be converted to sessionId before it's too late lol
    public void addRoutine(long time, long routineId) {
        routineIds.add(routineId);
    }

    public void addRoutine(long routineId) {
        Date date = new Date();
        addRoutine(date.getTime(), routineId);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public List<Long> getRoutineIds() {
      return routineIds;
    }

    public List<Long> getTimeStamps() {
      return timeStamps;
    }

    public void setTime(List<Long> timeStamps) {
      this.timeStamps = timeStamps;
    }
}
