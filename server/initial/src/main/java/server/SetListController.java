package server;

import java.util.ArrayList;
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
	        @RequestParam(required = false) Long id,
	        @RequestParam ArrayList<Double> listOfWeights,
            @RequestParam ArrayList<Integer> reps
	) {
		SetListEntity setList = new SetListEntity();
		if(id != null) setList.setId(id);
        if(listOfWeights != null)
            for(int i = 0; i < listOfWeights.size(); i++)
                setList.addSet(listOfWeights.get(i), reps.get(i));
        System.out.println(listOfWeights.size());
		SetListEntity s = setListRepository.save(setList);
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
