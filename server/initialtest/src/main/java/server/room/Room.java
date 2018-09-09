/**
 * Author: Olafur Palsson
 * HImail: olp6@gmail.com
 * Actual: olafur.palsson
 * Heiti verkefnis: server.room
 */

package server.room;

import server.availability.AvailabilityEntity;

public class Room extends RoomEntity {
	private AvailabilityEntity availability;

	public Room(String roomType, int numberOfBeds, boolean extraBed, long availabilityId) {
		super(roomType, numberOfBeds, extraBed, availabilityId);
	}

	public AvailabilityEntity getAvailability() {
		return availability;
	}

	public void setAvailability(AvailabilityEntity availability) {
		this.availability = availability;
	}
}
