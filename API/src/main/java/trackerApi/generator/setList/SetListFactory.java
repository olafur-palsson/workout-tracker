package trackerApi.generator.setList;

import trackerApi.generator.Entity;
import trackerApi.generator.Factory;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Olafur Palsson
 * HImail: olp6@gmail.com
 * Actual: olafur.palsson
 * Heiti verkefnis: PACKAGE_NAME
 */

public class SetListFactory<Ent extends Entity> extends Factory {
	public SetListFactory() {
		this.updateURL = "addSetList";
		this.getAllURL = "allAvailabilities";
		this.getOneURL = "oneSetList";
		this.removeURL = "removeSetList";
	}

	public SetList generate() {
		SetList setList = new SetList();
		return setList;
	}

	public Ent getOneSetList(long id) {
	    List<Ent> listOfOne = getOne(id);
	    return listOfOne.get(0);
    }

	@Deprecated
	public SetList jsonToEntity(JSONObject json) {
	    SetList setList = new SetList();
	    Long id = getAsLong("id", json);
	    List<Double> listOfWeights = getArrayListFromJSON("listOfWeights", json);
	    List<Integer> listOfReps = getArrayListFromJSON("listOfReps", json);
	    setList.setListOfWeights(listOfWeights);
	    setList.setListOfReps(listOfReps);
        setList.setId(id);
        System.out.println("Made the setlist");
		return setList;
	}
}
