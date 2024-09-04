package api;

import model.customer.Customer;
import model.room.IRoom;
import service.customers.CustomerService;
import service.reservation.ReservationService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AdminResource {
    private static final AdminResource ADMIN_RESOURCE = new AdminResource();
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
}
