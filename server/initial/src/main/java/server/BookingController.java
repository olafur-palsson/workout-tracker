package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import server.booking.BookingEntity;
import server.booking.BookingRepository;


@RestController    // This means that this class is a Controller
@RequestMapping(path="/database") // This means URL's start with /demo (after Application path)
public class BookingController {
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
}
