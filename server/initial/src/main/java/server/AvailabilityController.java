package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import server.availability.AvailabilityEntity;
import server.availability.AvailabilityRepository;


@RestController    // This means that this class is a Controller
@RequestMapping(path="/database") // This means URL's start with /demo (after Application path)
public class AvailabilityController {

	/*********************************
	 *   AVAILABILITY METHODS
	 ********************************/
	@Autowired
	private AvailabilityRepository availabilityRepository;

	@CrossOrigin
	@GetMapping(path = "/addAvailability")
	public @ResponseBody
	Long addAvailability(
		 @RequestParam Map<String, String> allParams
	) {
		AvailabilityEntity availability = new AvailabilityEntity();
		Map<Long, Integer> days = new HashMap<>();
		for(Map.Entry<String, String> e : allParams.entrySet()) {
			if(e.getKey().equals("id")) {
				availability.setId(Long.parseLong(e.getValue()));
				continue;
			}
			days.put(Long.parseLong(e.getKey()), Integer.parseInt(e.getValue()));
		}
		availability.setDays(days);
		AvailabilityEntity a =  availabilityRepository.save(availability);
		return a.getId();
	}

	@CrossOrigin
	@GetMapping(path = "/removeAvailability")
	public @ResponseBody String removeAvailability(
		 @RequestParam Long id
	) {
		availabilityRepository.delete(id);
		return "Deleted";
	}

	@CrossOrigin
	@GetMapping(path = "/oneAvailability")
	public @ResponseBody
	AvailabilityEntity getOneAvailability(
		 @RequestParam Long id
	) {
		return availabilityRepository.findOne(id);
	}

}
