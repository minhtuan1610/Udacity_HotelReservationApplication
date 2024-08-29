package model.reservation;

import model.customer.Customer;
import model.room.IRoom;

import java.util.Date;

public class Reservation {
	private final Customer customer;
	private final IRoom iRoom;
	private final Date checkInDate;
	private final Date checkOutDate;

	public Reservation(Customer customer, IRoom iRoom, Date checkInDate, Date checkOutDate) {
		this.customer = customer;
		this.iRoom = iRoom;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
	}

	public IRoom getiRoom() {
		return iRoom;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	@Override
	public String toString() {
		return "Customer: " + customer + ", Room: " + getiRoom() +
				", Check in: " + checkInDate + ", Check out: " + checkOutDate;
	}
}
