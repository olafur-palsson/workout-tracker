
package trackerApi.generator.exercise;

import trackerApi.generator.Entity;
import trackerApi.generator.Factory;
import javafx.util.Pair;

import java.util.*;

public class Exercise extends Entity {

    public Exercise() { }

    private String description = "No_description";
    private String name = "No_name";

	@Override
	public List<Pair<String, String>> getParameters() {
		List<Pair<String, String>> params = new ArrayList<>();
		if(this.id != null)
			params.add(new Pair<>("id", "" + this.id));
		params.add(pair("name", name));
		List<String> textFragments = Factory.splitStringToArrayList(description);
		for(String fragment : textFragments)
			params.add(pair("description", fragment));
		return params;
	}

	public String getName()             { return name;     }
	public void   setName(String name) { this.name = name; }

	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
}
