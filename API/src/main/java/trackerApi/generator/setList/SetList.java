
package trackerApi.generator.setList;


import trackerApi.generator.Entity;
import javafx.util.Pair;

import java.util.*;


public class SetList extends Entity {
	private Long id;

	private List<Double> listOfWeights = new ArrayList<>();
    private List<Integer> listOfReps = new ArrayList<>();

	public SetList() { }

    @Override
    public List<Pair<String, String>> getParameters() {
        List<Pair<String, String>> params = new ArrayList<>();
        if(this.id != null)
            params.add(new Pair<>("id", "" + this.id));
        for(Double weight : listOfWeights)
            params.add(pair("weight", weight));
        for(Integer reps : listOfReps)
            params.add(pair("reps", reps));
        return params;
    }

    public void addSet(double weight, int reps) {
        listOfWeights.add(weight);
        listOfReps.add(reps);
    }

    public void removeSet(int setNumber) throws ArrayIndexOutOfBoundsException {
        listOfWeights.remove(setNumber);
        listOfReps.remove(setNumber);
    }

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


    public List<Double> getListOfWeights() {
        return listOfWeights;
    }

    public void setListOfWeights(List<Double> listOfWeights) {
        this.listOfWeights = listOfWeights;
    }

    public List<Integer> getListOfReps() {
        return listOfReps;
    }

    public void setListOfReps(List<Integer> listOfReps) {
        this.listOfReps = listOfReps;
    }
}
