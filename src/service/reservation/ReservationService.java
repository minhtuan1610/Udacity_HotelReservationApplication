package service.reservation;

import model.customer.Customer;
import model.reservation.Reservation;
import model.room.IRoom;

import java.text.ParseException;
import java.util.*;

public class ReservationService {
    private static final ReservationService RESERVATION_SERVICE = new ReservationService();
    private static final int RECOMMENDED_DAY = 7;

    // Key: email
    private final Map<String, Collection<Reservation>> reservationList = new HashMap<>();

    // Key: room number
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
        roomList.putIfAbsent(iRoom.getRoomNumber(), iRoom);
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
        // Get all the booked rooms in the given interval date
        Set<IRoom> reservedRooms = new HashSet<>();
        // Get all reservation
        Collection<Reservation> reservations = getAllReservations();
        for (Reservation r : reservations) {
            try {
                if (!isValidationDate(r, checkInDate, checkOutDate)) {
                    reservedRooms.add(r.getIRoom());
                }
            } catch (ParseException e) {
                System.out.println("Date is not formatted");
            }
        }

        // Add the available room in list
        Collection<IRoom> roomCollection = roomList.values();
        if (reservedRooms.size() == 0) {
            availableRooms.addAll(roomCollection);
        } else {
            for (IRoom r : roomCollection) {
                if (!reservedRooms.contains(r)) {
                    availableRooms.add(r);
                }
            }
        }
        availableRooms.forEach(System.out::println);
        return availableRooms;
    }


    /**
     * Find the other rooms
     *
     * @param checkIn  Day after the checkInDate
     * @param checkOut After the checkOutDate
     * @return list of available rooms.
     */
    public Collection<IRoom> findAlternativeRoom(Date checkIn, Date checkOut) {
        Date checkInPlus = chooseAlterDate(checkIn);
        Date checkOutPlus = chooseAlterDate(checkOut);
        System.out.println("We have rooms from:" + checkInPlus + "to: " + checkOutPlus);
        return findRooms(checkInPlus, checkOutPlus);
    }

    public Date chooseAlterDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, RECOMMENDED_DAY);
        return cal.getTime();
    }

    /**
     * @param customer Customer has reserved rooms
     * @return customer from reservation list via email
     */
    public Collection<Reservation> getCustomersReservation(Customer customer) {
        System.out.println(reservationList.get(customer.getEmail()));
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

    public Collection<IRoom> getAllRooms() {
        roomList.values().forEach(System.out::println);
        return roomList.values();
    }

    private boolean isValidationDate(Reservation reservation, Date checkInDate, Date checkOutDate) throws ParseException {
        Date rIn = reservation.getCheckInDate();
        Date rOut = reservation.getCheckOutDate();
        // CheckOut < CheckIn, Low bound and Upper Bound
        if (checkInDate.after(checkOutDate) || rOut.equals(checkInDate) || rIn.equals(checkOutDate)) {
            return false;
        }
        return (rOut.before(checkInDate) || rIn.after(checkOutDate));
    }
}
