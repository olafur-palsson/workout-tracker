package server.data.setList;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * SetList is a list of sets (i.e. reps, weights and wether the exercise has been finished). It has a one-to-one relation to an exercise
 *
 */

@Entity // This tells Hibernate to make a table out of this class
public class SetListEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ElementCollection
    private List<Double> listOfWeights = new ArrayList<>();
    @ElementCollection
    private List<Integer> listOfReps = new ArrayList<>();
    @ElementCollection
    private List<Boolean> finishedSets = new ArrayList<>();

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setListOfWeights(List<Double> listOfWeights) {
        this.listOfWeights = listOfWeights;
    }
    public List<Double> getListOfWeights() {
        return listOfWeights;
    }

    public void setListOfReps(List<Integer> listOfReps) {
        this.listOfReps = listOfReps;
    }
    public List<Integer> getListOfReps() {
        return listOfReps;
    }

    public void setFinishedSets(List<Boolean> finishedSets) {
        this.finishedSets = finishedSets;
    }
    public List<Boolean> getFinishedSets() {
        return finishedSets;
    }

    public void addSet(double weight, int reps, Boolean isDone) {
        listOfWeights.add(weight);
        listOfReps.add(reps);
        finishedSets.add(isDone);
    }

    // thad tharf ad hondla thennan error svo front-end laetur ekki serverinn
    // krassa, lol
    public void removeSet(int setNumber) throws ArrayIndexOutOfBoundsException {
        listOfWeights.remove(setNumber);
        listOfReps.remove(setNumber);
    }
}
