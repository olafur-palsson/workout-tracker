package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import server.room.RoomEntity;
import server.room.RoomRepository;



@RestController    // This means that this class is a Controller
@RequestMapping(path="/database") // This means URL's start with /demo (after Application path)
public class RoomController {



	/*********************************
	 *   ROOM METHODS
	 ********************************/
	@Autowired
	private RoomRepository roomRepository;

	@CrossOrigin
	@GetMapping(path = "/addRoom")
	public @ResponseBody
	String addNewRoom(
		 @RequestParam(required = false) Long id,
		 @RequestParam String roomType,
		 @RequestParam Integer numberOfBeds,
		 @RequestParam Boolean extraBed,
		 @RequestParam Long availabilityId
	) {
		RoomEntity re = new RoomEntity(roomType, numberOfBeds, extraBed);
		if(id != null) re.setId(id);
		re.setAvailabilityId(availabilityId);
		re = roomRepository.save(re);
		return "" + re.getId();
	}

	@CrossOrigin
	@GetMapping(path = "/oneRoom")
	public @ResponseBody
	RoomEntity getOneRoom(
		 @RequestParam Long id
	) {
		return roomRepository.findOne(id);
	}

	@CrossOrigin
	@GetMapping(path = "/removeRoom")
	public @ResponseBody String removeRoom(
		 @RequestParam Long id
	) {
		roomRepository.delete(id);
		return "Deleted";
	}

}
