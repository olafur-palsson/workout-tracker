package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import server.hotel.HotelEntity;
import server.hotel.HotelRepository;



@RestController    // This means that this class is a Controller
@RequestMapping(path="/database") // This means URL's start with /demo (after Application path)
public class HotelController {

	/*********************************
	 *   HOTEL METHODS
	 ********************************/
	@Autowired
	private HotelRepository hotelRepository;

	@CrossOrigin
	@GetMapping(path = "/addHotel")
	public @ResponseBody
	Long addNewHotel(
		 @RequestParam(required = false) Long id,
		 @RequestParam(required = false) ArrayList<String> amenities,
		 @RequestParam(required = false) ArrayList<Long> roomIds,
		 @RequestParam(required = false) ArrayList<Long> reviewIds,
		 @RequestParam (required = false) ArrayList<String> description,

		 @RequestParam String name,
		 @RequestParam String email,
		 @RequestParam double latitude,
		 @RequestParam Double longitude,
		 @RequestParam Integer numRooms,
		 @RequestParam String imageUrl,
		 @RequestParam Integer stars
	) {
		HotelEntity h = new HotelEntity();
		h.setName(name);
		h.setEmail(email);
		h.setLatitude(latitude);
		h.setLongitude(longitude);
		h.setNumRooms(numRooms);
		h.setImageUrl(imageUrl);
		h.setStars(stars);
		if(roomIds != null) 	 h.setRoomIds  ((Map<Integer, Long>)   Converter.arrayListToMap(roomIds));
		if(reviewIds != null) 	 h.setReviewIds  ((Map<Integer, Long>)   Converter.arrayListToMap(reviewIds));
		if(amenities != null) h.setAmenities((Map<Integer, String>) Converter.arrayListToMap(amenities));
		if(description != null) h.setDescription((Map<Integer, String>) Converter.arrayListToMap(description));
		if(id != null)        h.setId(id);
		h = hotelRepository.save(h);
		return h.getId();
	}

	@CrossOrigin
	@GetMapping(path = "/oneHotel")
	public @ResponseBody
	HotelEntity getOneHotel(
		 @RequestParam Long id
	)
	{
		return hotelRepository.findOne(id);
	}

	@CrossOrigin
	@GetMapping(path = "/removeHotel")
	public @ResponseBody String removeHotel(
		 @RequestParam Long id
	) {
		hotelRepository.delete(id);
		return "Deleted";
	}
}
