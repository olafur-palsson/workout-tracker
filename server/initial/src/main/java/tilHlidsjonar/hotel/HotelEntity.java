
package server.hotel;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class HotelEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Integer numRooms;
	private Double  latitude;
	private Double longitude;
	private String  name;
	private String  email;
	private String imageUrl;
	private Integer stars;

	//roomType, ID
	@ElementCollection
	private Map<Integer, Long> roomIds;
	@ElementCollection
	private Map<Integer, String> amenities;
	@ElementCollection
	private Map<Integer, Long> reviewIds;
	//private Date[] closedDates;
	@ElementCollection
	private Map<Integer, String> description;



	public HotelEntity() { }

	public HotelEntity extractEntity() {
		HotelEntity h = new HotelEntity();
		h.setId(id);
		h.setNumRooms(numRooms);
		h.setLatitude(latitude);
		h.setLongitude(longitude);
		h.setName(name);
		h.setStars(stars);
		h.setImageUrl(imageUrl);
		h.setDescription(description);
		h.setRoomIds(roomIds);
		h.setAmenities(amenities);
		h.setReviewIds(reviewIds);
		return h;
	}

	public Long getId()           { return id;     }
	public void    setId(Long id) { this.id = id;  }

	public Integer getNumRooms()                 { return numRooms;          }
	public void    setNumRooms(Integer numRooms) { this.numRooms = numRooms; }

	public double getLatitude()                 { return latitude;         }
	public void   setLatitude(double latitude) { this.latitude = latitude; }

	public Double getLongitude()                   { return longitude;            }
	public void setLongitude(Double longitude)  { this.longitude = longitude; }

	public String getName()             { return name;     }
	public void   setName(String name) { this.name = name; }

	public String getEmail()             { return email;       }
	public void   setEmail(String email) { this.email = email; }

	public void setAmenities(Map<Integer, String> amenities) {
		this.amenities = amenities;
	}
	public Map<Integer, String> getAmenities() { return this.amenities; }

	public void setRoomIds(HashMap<Integer, Long> rooms) { this.roomIds = rooms; }
	public Map<Integer, Long> getRoomIds() { return roomIds; }

	public void addRoomId(Long roomEntityId) {
		roomIds.put(roomIds.size(), roomEntityId);
	}

	public void setRoomIds(Map<Integer, Long> roomEntityList) {
		Map<Integer, Long> newRooms = new HashMap<>();
		for(Map.Entry<Integer, Long> e : roomEntityList.entrySet())
			newRooms.put(newRooms.size(), e.getValue());
		this.roomIds = newRooms;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getStars() {
		return stars;
	}

	public void setStars(Integer stars) {
		this.stars = stars;
	}

	public Map<Integer, Long> getReviewIds() {
		return reviewIds;
	}

	public void setReviewIds(Map<Integer, Long> reviewIds) {
		this.reviewIds = reviewIds;
	}

	public Map<Integer, String> getDescription() {
		return description;
	}

	public void setDescription(Map<Integer, String> description) {
		this.description = description;
	}
}

