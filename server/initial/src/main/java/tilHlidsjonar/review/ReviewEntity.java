package server.review;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity // This tells Hibernate to make a table out of this class
public class ReviewEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Long userId;
	private Long hotelId;
	private String subject;
	@ElementCollection
	private Map<Integer, String> reviewText;
	private Double rating;

	public ReviewEntity() {}

	public ReviewEntity(Long id, Long userId, Long hotelId, String subject, Map<Integer, Long> bookings) {
		this.id = id;
		this.userId = userId;
		this.hotelId = hotelId;
		this.subject = subject;
	}

	public ReviewEntity extractEntity() {
		ReviewEntity review = new ReviewEntity();
		review.setId(id);
		review.setUserId(userId);
		review.setHotelId(hotelId);
		review.setSubject(subject);
		review.setReviewText(reviewText);
		review.setRating(rating);
		return review;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Map<Integer, String> getReviewText() {
		return reviewText;
	}

	public void setReviewText(Map<Integer, String> reviewText) {
		this.reviewText = reviewText;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}
}
