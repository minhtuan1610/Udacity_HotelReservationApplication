package api;

import model.customer.Customer;
import model.room.IRoom;
import service.customers.CustomerService;
import service.reservation.ReservationService;

import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminResource {
    private static final AdminResource ADMIN_RESOURCE = new AdminResource();
    private static final String REGEX_ROOM = "^\\d+$";
    private final CustomerService customerService = CustomerService.getCustomerServiceInstance();
    private final ReservationService reservationService = ReservationService.getReservationServiceInstance();

    private AdminResource() {
    }

    public static AdminResource getAdminResourceInstance() {
        return ADMIN_RESOURCE;
    }

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public void addRoom(List<IRoom> rooms) {
        rooms.forEach(reservationService::addRoom);
    }

    public Collection<IRoom> getAllRooms() {
        return reservationService.getAllRooms();
    }

    public Collection<Customer> getAllCustomers() {
        return customerService.getAllCustomer();
    }

    public void displayAllReservations() {
        reservationService.printAllReservation();
    }

    public boolean isValidRoomNumber(String roomNumber) {
        Pattern pattern = Pattern.compile(REGEX_ROOM);
        Matcher matcher = pattern.matcher(roomNumber);
        return matcher.matches();
    }
}
