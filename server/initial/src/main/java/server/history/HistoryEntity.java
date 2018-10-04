
package server.history;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class HistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private HashMap<Long, Long> routineIds = new HashMap<>();
    public HistoryEntity() { }

    public void addRoutine(long routineId, long time) {
        routineIds.put(routineId, time);
    }

    public void addRoutine(long routineId) {
        Date date = new Date();
        addRoutine(routineId, date.getTime());
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
