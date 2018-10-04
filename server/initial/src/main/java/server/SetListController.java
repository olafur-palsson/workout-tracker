package server;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import server.setList.SetListEntity;
import server.setList.SetListRepository;


@RestController    // This means that this class is a Controller
@RequestMapping(path="/database") // This means URL's start with /demo (after Application path)
public class SetListController {

	@Autowired
	private SetListRepository setListRepository;

	@CrossOrigin
	@GetMapping(path = "/addSetList")
	public @ResponseBody
	Long addSetList(
		 @RequestParam Map<String, Long> allParams
	) {
		SetListEntity setList = new SetListEntity();
		Map<Integer, Integer> sets = new HashMap<>();
		for(Map.Entry<String, Long> e : allParams.entrySet()) {
			if(e.getKey().equals("id")) {
				setList.setId(e.getValue());
				continue;
			}
			double weight = Double.parseDouble(e.getKey());
			int reps = Math.toIntExact(e.getValue());
			setList.addSet(weight, reps);
		}
		SetListEntity s =  setListRepository.save(setList);
		return s.getId();
	}

	@CrossOrigin
	@GetMapping(path = "/removeSetList")
	public @ResponseBody String removeSetList(
		 @RequestParam Long id
	) {
		setListRepository.delete(id);
		return "Deleted";
	}

	@CrossOrigin
	@GetMapping(path = "/oneSetList")
	public @ResponseBody
	SetListEntity getOneSetList(
		 @RequestParam Long id
	) {
		return setListRepository.findOne(id);
	}

}
