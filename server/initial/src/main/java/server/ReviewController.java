package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import server.review.ReviewEntity;
import server.review.ReviewRepository;



@RestController    // This means that this class is a Controller
@RequestMapping(path="/database") // This means URL's start with /demo (after Application path)
public class ReviewController {


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
