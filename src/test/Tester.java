package test;

import model.customer.Customer;
import model.reservation.Reservation;
import model.room.Room;
import service.CustomerService;

import java.util.Date;

public class Tester {
    public static void main(String[] args) {
        Customer customer = new Customer("test", "test", "test@domain.com");
        Room room = new Room();
        Date checkIn = new Date(2024 - 3 - 3);
        Date checkOut = new Date(2024 - 3 - 4);
        CustomerService service = new CustomerService();
        Reservation reservation = new Reservation(customer, room, checkIn, checkOut);
        System.out.println(customer);
        System.out.println(reservation);
        System.out.println(service);
    }
}
