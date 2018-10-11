package trackerApi.generator.history;

import trackerApi.generator.Factory;
import org.json.JSONObject;

import java.util.Map;
import java.util.List;

/**
 * Author: Olafur Palsson
 * HImail: olp6@gmail.com
 * Actual: olafur.palsson
 * Heiti verkefnis: PACKAGE_NAME
 */

public class HistoryFactory<Ent extends History> extends Factory {
	public HistoryFactory() {
		this.updateURL = "addHistory";
		this.getAllURL = "allHistory";
		this.getOneURL = "oneHistory";
		this.removeURL = "removeHistory";
	}

	public Ent getOneHistory(Long id) {
		List<Ent> list = getOne(id);
		return list.get(0);
	}

	@Override
	public History generate() {
		History history = new History();
		return history;
	}

	@Override
	public History jsonToEntity(JSONObject json) {
		Long id = getAsLong("id", json);
		History history = new History();
		history.setId(id);
		return history;
	}
}
