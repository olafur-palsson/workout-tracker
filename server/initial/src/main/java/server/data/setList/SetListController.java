package server.data.setList;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import server.data.setList.SetListEntity;
import server.data.setList.SetListRepository;


@RestController    // This means that this class is a Controller
@RequestMapping(path="/database") // This means URL's start with /demo (after Application path)
public class SetListController {

	@Autowired
	private SetListRepository setListRepository;

	@CrossOrigin
	@GetMapping(path = {"/addSetList", "/userEnabled/addSetList"})
	public @ResponseBody
	Long addSetList(
	        @RequestParam(required = false) Long id,
	        @RequestParam ArrayList<Double> weight,
            @RequestParam ArrayList<Integer> reps,
            @RequestParam(required = false) ArrayList<Boolean> setIsDone
	) {
		SetListEntity setList = new SetListEntity();
		if(id != null) setList.setId(id);
        if(weight != null)
            for(int i = 0; i < weight.size(); i++) {
                Boolean isDone = false;
                if(setIsDone != null)
                    isDone = setIsDone.get(i);
                setList.addSet(weight.get(i), reps.get(i), isDone);
            }
		SetListEntity s = setListRepository.save(setList);
		return s.getId();
	}

	@CrossOrigin
	@GetMapping(path = {"/removeSetList", "/userEnabled/removeSetList"})
	public @ResponseBody String removeSetList(
		 @RequestParam Long id
	) {
		setListRepository.delete(id);
		return "Deleted";
	}

	@CrossOrigin
	@GetMapping(path = {"/oneSetList", "/userEnabled/oneSetList"})
	public @ResponseBody
	SetListEntity getOneSetList(
		 @RequestParam Long id
	) {
		return setListRepository.findOne(id);
	}

}
