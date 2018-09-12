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

}
