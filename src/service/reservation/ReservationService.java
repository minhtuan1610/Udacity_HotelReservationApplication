package service.reservation;

import model.customer.Customer;
import model.reservation.Reservation;
import model.room.IRoom;
import model.room.Room;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReservationService {
	private static final ReservationService RESERVATION_SERVICE = new ReservationService();
	private final Map<String, Collection<Reservation>> reservationList = new HashMap<>();
	private final Map<String, IRoom> roomList = new HashMap<>();

	private ReservationService() {
	}

	public static ReservationService getReservationServiceInstance() {
		return RESERVATION_SERVICE;
	}

	/**
	 * Use to add an object Room into a list
	 *
	 * @param iRoom Instance of Room
	 */
	public void addRoom(IRoom iRoom) {
		roomList.put(iRoom.getRoomNumber(), iRoom);
	}

	/**
	 * Use to get the room from a room list
	 *
	 * @param roomID Room number
	 * @return the room number
	 */
	public IRoom getARoom(String roomID) {
		return roomList.get(roomID);
	}

	/**
	 * Use to book a room
	 *
	 * @param customer     An object containing the information of customer. Key: email
	 * @param iRoom        Room for customer. Key: roomNumber
	 * @param checkInDate  check-in date
	 * @param checkOutDate check-out date
	 * @return a reserved room in reservation list
	 */
	public Reservation reserveARoom(Customer customer, IRoom iRoom, Date checkInDate, Date checkOutDate) {
		// One customer can reserve more than one room
		Collection<Reservation> customerReservation = getCustomersReservation(customer);
		if (customerReservation == null) {
			customerReservation = new ArrayList<>();
		}
		Reservation reservation = new Reservation(customer, iRoom, checkInDate, checkOutDate);
		customerReservation.add(reservation);
		reservationList.put(customer.getEmail(), customerReservation);
		return reservation;
	}

	/**
	 * Find all the available room in the interval time
	 *
	 * @param checkInDate  start date
	 * @param checkOutDate end date
	 * @return all the available rooms.
	 */
	public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
		List<IRoom> availableRooms = new ArrayList<>();
		// Get all the booked rooms
		Collection<Reservation> reservations = getAllReservations();
		// Check the booked rooms in the given interval time
		Set<IRoom> reservedRooms = new HashSet<>();
		for (Reservation r : reservations) {
			try {
				if (RESERVATION_SERVICE.isValidationDate(r, checkInDate, checkOutDate)) {
				}

			} catch (ParseException e) {
				System.out.println("Date is not formatted");
			}
		}


		return availableRooms;
	}

	/**
	 * @param customer Customer has reserved rooms
	 * @return customer from reservation list via email
	 */
	public Collection<Reservation> getCustomersReservation(Customer customer) {
		return reservationList.get(customer.getEmail());
	}

	public void printAllReservation() {
		Collection<Reservation> reservations = getAllReservations();
		if (reservations.isEmpty()) {
			System.out.println("No reservation");
		}
		reservations.forEach(System.out::println);
	}

	private Collection<Reservation> getAllReservations() {
		Collection<Reservation> reservations = new ArrayList<>();

        /*
         This is method reference.It is equivalent with this block code
            for (Collection<Reservation> res : reservationList.values()) {
                reservations.addAll(res);
            }
        */
		reservationList.values().forEach(reservations::addAll);
		return reservations;
	}

	private boolean isValidationDate(Reservation reservation, Date checkInDate, Date checkOutDate) throws ParseException {
		boolean isValid = false;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String cid = simpleDateFormat.format(checkInDate);
		String cod = simpleDateFormat.format(checkOutDate);
		String rCid = simpleDateFormat.format(reservation.getCheckInDate());
		String rCod = simpleDateFormat.format(reservation.getCheckOutDate());
		Date checkIn = simpleDateFormat.parse(cid);
		Date checkOut = simpleDateFormat.parse(cod);
		Date rIn = simpleDateFormat.parse(rCid);
		Date rOut = simpleDateFormat.parse(rCod);
		if (rIn.before(checkIn) && rOut.after(checkOut)) {
			isValid = true;
		}

		return isValid;
	}
}
