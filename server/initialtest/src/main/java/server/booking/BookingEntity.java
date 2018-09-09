/**
 * Author: Olafur Palsson
 * HImail: olp6@gmail.com
 * Actual: olafur.palsson
 * Heiti verkefnis: server
 */

package server.booking;


import server.Converter;
import server.ToolBox;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity // This tells Hibernate to make a table out of this class
public class BookingEntity {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	private Long hotelId;
	private Long userId;
	private Long roomId;
	private Boolean isPaid;
	private Long dateFrom;
	private Long dateTo;
	private String cc;
	private Boolean isCancelled;

	public BookingEntity() {}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public boolean getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(boolean paid) {
		isPaid = paid;
	}

	public Long getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Long from) {
		assert from / 86400000 == 0;
		this.dateFrom = from;
	}

	public Long getDateTo() {
		return dateTo;
	}

	public void setDateTo(Long to) {
		assert to / 86400000 == 0;
		this.dateTo = to;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public Boolean getIsCancelled() {
		return isCancelled;
	}

	public void setIsCancelled(Boolean cancelled) {
		isCancelled = cancelled;
	}
}

