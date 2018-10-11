package server.setList;

	 import javax.persistence.*;
     import java.util.ArrayList;
     import java.util.List;

@Entity // This tells Hibernate to make a table out of this class
public class SetListEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@ElementCollection
	private List<Double> listOfWeights = new ArrayList<>();
	@ElementCollection
	private List<Integer> listOfReps = new ArrayList<>();

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

	public void addSet(double weight, int reps) {
	    listOfWeights.add(weight);
	    listOfReps.add(reps);
	}

	// thad tharf ad hondla thennan error svo front-end laetur ekki serverinn
    // krassa, lol
	public void removeSet(int setNumber) throws ArrayIndexOutOfBoundsException {
	    listOfWeights.remove(setNumber);
        listOfReps.remove(setNumber);
    }
}
