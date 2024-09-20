package api;

import model.customer.Customer;
import model.reservation.Reservation;
import model.room.IRoom;
import service.customers.CustomerService;
import service.reservation.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    private static final HotelResource HOTEL_RESOURCE = new HotelResource();

    private final CustomerService customerService = CustomerService.getCustomerServiceInstance();
    private final ReservationService reservationService = ReservationService.getReservationServiceInstance();
    private Date checkIn;
    private Date checkOut;

    private HotelResource() {
    }

    public static HotelResource getHotelResourceInstance() {
        return HOTEL_RESOURCE;
    }

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public void createCustomer(String email, String firstName, String lastName) {
        customerService.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber) {
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        Customer cus = getCustomer(customerEmail);
        return reservationService.reserveARoom(cus, room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomerReservations(String customerEmail) {
        Customer cus = getCustomer(customerEmail);
        return reservationService.getCustomersReservation(cus);
    }

    public void listCustomerReservation(String cusEmail) {
        Customer cus = getCustomer(cusEmail);
        reservationService.listCustomerReservation(cus);
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut) {
        return reservationService.findRooms(checkIn, checkOut);
    }

    public Collection<IRoom> findAlternativeRoom(Date checkIn, Date checkOut) {
        return reservationService.findAlternativeRoom(checkIn, checkOut);
    }

    public Date chooseAlterDate(Date date) {
        return reservationService.chooseAlterDate(date);
    }
}
