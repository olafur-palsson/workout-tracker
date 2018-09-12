package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import server.availability.AvailabilityEntity;
import server.availability.AvailabilityRepository;
import server.booking.BookingEntity;
import server.booking.BookingRepository;
import server.hotel.HotelEntity;
import server.hotel.HotelRepository;
import server.review.ReviewEntity;
import server.review.ReviewRepository;
import server.room.RoomEntity;
import server.room.RoomRepository;
import server.user.UserEntity;
import server.user.UserRepository;

@RestController    // This means that this class is a Controller
@RequestMapping(path="/database") // This means URL's start with /demo (after Application path)
public class MainController {


	/*********************************
	 *   USER METHODS
	 ********************************/
	@Autowired
	private UserRepository userRepository;

	@CrossOrigin
	@GetMapping(path = "/addUser") // Map ONLY GET Requests
	public @ResponseBody
	String addNewUser(
		 @RequestParam(required = false) Long id,
		 @RequestParam(required = false) ArrayList<Long> bookingIds,
		 @RequestParam(required = false) ArrayList<Long> reviewIds,
		 @RequestParam String name,
		 @RequestParam String email
	) {
		UserEntity u = new UserEntity();
		if(id != null) 		  u.setId(id);
		if(bookingIds != null) u.setBookingIds((Map<Integer, Long>) Converter.arrayListToMap(bookingIds));
		if(reviewIds != null) u.setReviewIds((Map<Integer, Long>) Converter.arrayListToMap(bookingIds));
		u.setName(name);
		u.setEmail(email);
		u = userRepository.save(u);
		return u.getId().toString();
	}

	@CrossOrigin
	@GetMapping(path = "/oneUser")
	public @ResponseBody
	UserEntity getOneUser(
		 @RequestParam Long id
	) {
		return userRepository.findOne(id);
	}

	@CrossOrigin
	@GetMapping(path = "/allUsers")
	public @ResponseBody
	Iterable<UserEntity> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}

	@CrossOrigin
	@GetMapping(path = "/addBookingToUser")
	public @ResponseBody
	String addBookingToUser(
		 @RequestParam Long userId,
		 @RequestParam Long bookingId
	) {
		UserEntity user = userRepository.findOne(userId);
		user.addBookingId(bookingId);
		return "Done";
	}

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
	@GetMapping(path = "/getHotelsByLocation")
	public @ResponseBody
	Iterable<HotelEntity> getHotelsByLocation(
		 @RequestParam Double latitude,
		 @RequestParam Double longitude
	) {
		Iterable<HotelEntity> list = getAllHotels();
		List<HotelEntity> closeHotels = new ArrayList<>();
		for(HotelEntity he : list) {
			if(ToolBox.isClose(latitude, he.getLatitude(), longitude, he.getLongitude()))
				closeHotels.add(he);
		}
		return closeHotels;
	}

	@CrossOrigin
	@Cacheable("hotelCache")
	@GetMapping(path = "/allHotels")
	public @ResponseBody
	Iterable<HotelEntity> getAllHotels() {
		return hotelRepository.findAll();
	}

	@CrossOrigin
	@GetMapping(path = "/removeHotel")
	public @ResponseBody String removeHotel(
		 @RequestParam Long id
	) {
		hotelRepository.delete(id);
		return "Deleted";
	}

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

	/*********************************
	 *   BOOKING METHODS
	 ********************************/
	@Autowired
	private BookingRepository bookingRepository;

	@CrossOrigin
	@GetMapping(path = "/oneBooking")
	public @ResponseBody
	BookingEntity getOneBooking(
		 @RequestParam Long id
	) {
		return bookingRepository.findOne(id);
	}

	//return bookingID
	@CrossOrigin
	@GetMapping(path = "/addBooking")
	public @ResponseBody String addBooking(
		 @RequestParam(required = false) Long id,
		 @RequestParam Long hotelId,
		 @RequestParam Long roomId,
		 @RequestParam Long userId,
		 @RequestParam String dateFrom, //yyyy-mm-dd
		 @RequestParam String dateTo,    //yyyy-mm-dd
		 @RequestParam Boolean isPaid,
		 @RequestParam Boolean isCancelled,
		 @RequestParam String cc
	) {
		BookingEntity booking = new BookingEntity();
		Long from = Converter.yyyymmdd_toLong(dateFrom);
		Long to = Converter.yyyymmdd_toLong(dateTo);
		if(id != null) booking.setId(id);
		booking.setHotelId(hotelId);
		booking.setUserId(userId);
		booking.setRoomId(roomId);
		booking.setHotelId(hotelId);
		booking.setDateFrom(from);
		booking.setDateTo(to);
		booking.setIsPaid(isPaid);
		booking.setIsCancelled(isCancelled);
		booking.setCc(cc);
		booking = bookingRepository.save(booking);
		return booking.getId().toString();
	}

	@CrossOrigin
	@GetMapping(path = "/removeBooking")
	public @ResponseBody String removeBooking(
		 @RequestParam Long id
	) {
		bookingRepository.delete(id);
		return "Deleted";
	}

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
	/*********************************
	 *   REVIEW METHODS
	 ********************************/
	@Autowired
	private ReviewRepository reviewRepository;

	@CrossOrigin
	@GetMapping(path = "/allReview")
	public @ResponseBody
	Iterable<ReviewEntity> getAllReviews() {
		return reviewRepository.findAll();
	}

	@CrossOrigin
	@GetMapping(path = "/oneReview")
	public @ResponseBody
	ReviewEntity getOneReview(
		 @RequestParam Long id
	) {
		return reviewRepository.findOne(id);
	}

	@CrossOrigin
	@GetMapping(path = "/removeReview")
	public @ResponseBody String removeReview(
		 @RequestParam Long id
	) {
		reviewRepository.delete(id);
		return "Deleted";
	}

	@CrossOrigin
	@GetMapping(path = "/addReview")
	public @ResponseBody
	Long addReview(
		 @RequestParam (required = false) Long id,
		 @RequestParam (required = false) ArrayList<String> reviewText,

		 @RequestParam Long userId,
		 @RequestParam Long hotelId,
		 @RequestParam String subject,
		 @RequestParam Double rating
	) {
		ReviewEntity review = new ReviewEntity();
		if(id != null)         review.setId(id);
		if(reviewText != null) review.setReviewText((Map<Integer, String>) Converter.arrayListToMap(reviewText));
		review.setUserId(userId);
		review.setHotelId(hotelId);
		review.setSubject(subject);
		review.setRating(rating);
		review = reviewRepository.save(review);
		return review.getId();
	}
}
