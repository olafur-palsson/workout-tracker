package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import server.history.HistoryEntity;
import server.history.HistoryRepository;



@RestController    // This means that this class is a Controller
@RequestMapping(path="/database") // This means URL's start with /demo (after Application path)
public class HistoryController {

    @Autowired
	private HistoryRepository historyRepository;

	@CrossOrigin
	@GetMapping(path = "/addHistory")
	public @ResponseBody
	Long addNewHistory(
		 @RequestParam(required = false) Long id,
         @RequestParam(required = false) ArrayList<Long> routineIds,
         @RequestParam(required = false) ArrayList<Long> timeStamps
	) {
		HistoryEntity h = new HistoryEntity();
        if(id != null)        h.setId(id);

		h = historyRepository.save(h);
		return h.getId();
	}

	@CrossOrigin
	@GetMapping(path = "/oneHistory")
	public @ResponseBody
	HistoryEntity getOneHistory(
		 @RequestParam Long id
	)
	{
		return historyRepository.findOne(id);
	}

	@CrossOrigin
	@GetMapping(path = "/removeHistory")
	public @ResponseBody String removeHistory(
		 @RequestParam Long id
	) {
		historyRepository.delete(id);
		return "Deleted";
	}
}
