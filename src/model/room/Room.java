package model.room;

import java.util.Objects;

public class Room implements IRoom {
	private String roomNumber;
	private Double price;
	private RoomType enumeration;

	public Room() {
	}

	public Room(String roomNumber, Double price, RoomType enumeration) {
		this.roomNumber = roomNumber;
		this.price = price;
		this.enumeration = enumeration;
	}

	/**
	 *
	 * @return the room number
	 */
	@Override
	public String getRoomNumber() {
		return roomNumber;
	}

	/**
	 *
	 * @return the price for a room
	 */
	@Override
	public Double getRoomPrice() {
		return price;
	}

	/**
	 *
	 * @return the type of room: single or double
	 */
	@Override
	public RoomType getRoomType() {
		return enumeration;
	}

	/**
	 *
	 * @return status of room: booking or not
	 */
	@Override
	public boolean isFree() {
		return price.equals(0.0);
	}


	/**
	 *
	 * @param obj object to compare
	 * @return true/false if object is equal or not
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		// Ensures that it will return false if the argument is a subclass of a class compared.
		if (obj == null || (obj.getClass() != this.getClass())) {
			return false;
		}
		Room room = (Room) obj;
		return roomNumber.equals(room.roomNumber);
	}

	/**
	 *
	 * @return the hash code of room number
	 */
	@Override
	public int hashCode() {
		return Objects.hash(roomNumber);
	}

	@Override
	public String toString() {
		return "Room Number: " + roomNumber + ", Price: " + price + ", Room Type: " + enumeration;
	}
}
