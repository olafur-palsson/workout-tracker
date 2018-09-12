package server.user;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity // This tells Hibernate to make a table out of this class
public class UserEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private String email;
	@ElementCollection
	private Map<Integer, Long> reviewIds = new HashMap<>();
	@ElementCollection
	private Map<Integer, Long> bookingIds = new HashMap<>();

	public UserEntity() {}

	public UserEntity extractEntity() {
		UserEntity u = new UserEntity();
		u.setId(id);
		u.setName(name);
		u.setEmail(email);
		u.setReviewIds(reviewIds);
		u.setBookingIds(bookingIds);
		return u;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Map<Integer, Long> getBookingIds() { return bookingIds; }
	public void setBookingIds(Map<Integer, Long> bookings) {this.bookingIds = bookings; }

	public void addBookingId(Long bookingId) {
		int bookingNumber = bookingIds.size();
		this.bookingIds.put(bookingNumber, bookingId);
	}

	public Map<Integer, Long> getReviewIds() {
		return reviewIds;
	}

	public void setReviewIds(Map<Integer, Long> reviewIds) {
		this.reviewIds = reviewIds;
	}
}
