
package server.data.history;

import java.util.Date;
import java.util.HashMap;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class HistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    // <date as long, routineId
    private HashMap<Long, Long> routineIds = new HashMap<>();
    // Should be converted to sessionId before it's too late lol
    public void addRoutine(long time, long routineId) {
        routineIds.put(time, routineId);
    }

    public void addRoutine(long routineId) {
        Date date = new Date();
        addRoutine(date.getTime(), routineId);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public HashMap<Long, Long> getRoutineIds() {
        return routineIds;
    }
    public void setRoutineIds(HashMap<Long, Long> routineIds) {
        this.routineIds = routineIds;
    }
}
