import api.DataInput;
import api.HotelResource;
import model.customer.Customer;
import model.room.IRoom;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.InputMismatchException;


public class MainMenu {
    private static final HotelResource HOTEL_RESOURCE = HotelResource.getHotelResourceInstance();
    private static final DataInput INPUT = DataInput.getDataInput();
    private static int choice = -1;

    public static void mainMenu() {
        while (choice != 0) {
            displayMainMenu();
            choice = INPUT.getIntInput();
            switch (choice) {
                case 1 -> {
                    // Find rooms
                    Date checkIn = null;
                    Date checkOut = null;
                    boolean valid = false;
                    while (!valid) {
                        try {
                            System.out.println("Enter CheckIn Date dd/MM/yyyy. Example: 02/01/2024");
                            checkIn = INPUT.getDateInput();
                            System.out.println("Enter CheckOut Date dd/MM/yyyy. Example: 02/01/2024");
                            checkOut = INPUT.getDateInput();
                            if (checkIn.after(checkOut) || checkIn.before(Calendar.getInstance().getTime())
                                    || checkOut.before(Calendar.getInstance().getTime())) {
                                System.out.println("Wrong date. Check-In must be before Check-Out. In present");
                            } else {
                                valid = true;
                            }
                        } catch (ParseException e) {
                            System.out.println("Wrong format date");
                        }
                    }
                    Collection<IRoom> roomFound = HOTEL_RESOURCE.findARoom(checkIn, checkOut);
                    Collection<IRoom> roomAlterFound = roomFound.isEmpty() ? HOTEL_RESOURCE.findAlternativeRoom(checkIn, checkOut) : null;

                    if (!roomFound.isEmpty() || !roomAlterFound.isEmpty()) {
                        // Reserve a room
                        System.out.println("Would you like to book a room? (Please type y/n)");
                        String text = INPUT.getStringInput();
                        while (!text.equalsIgnoreCase("y") && !text.equalsIgnoreCase("n")) {
                            System.out.println("Would you like to book a room? (Please type y/n)");
                            text = INPUT.getStringInput();
                        }
                        if (text.equalsIgnoreCase("y")) {
                            try {
                                System.out.println("Enter your email:");
                                String email = INPUT.getStringInput();
                                System.out.println("Choose room number:");
                                String rNumber = INPUT.getStringInput();
                                if (HOTEL_RESOURCE.getRoom(rNumber) == null) {
                                    System.out.println("Room number does not exist");
                                } else if (roomFound.isEmpty()) {
                                    HOTEL_RESOURCE.bookARoom(email, HOTEL_RESOURCE.getRoom(rNumber),
                                            HOTEL_RESOURCE.chooseAlterDate(checkIn), HOTEL_RESOURCE.chooseAlterDate(checkOut));
                                } else {
                                    HOTEL_RESOURCE.bookARoom(email, HOTEL_RESOURCE.getRoom(rNumber), checkIn, checkOut);
                                }
                            } catch (IllegalArgumentException | NullPointerException e) {
                                System.out.println("Email is invalid or non-exist. Please try again. Email should be formatted as something@domain.com");
                            }
                        }
                    } else {
                        System.out.println("There is no room in this time.");
                    }

                }
                case 2 -> {
                    // See my reservations
                    try {
                        System.out.println("Enter your email:");
                        String eCus = INPUT.getStringInput();
                        HOTEL_RESOURCE.listCustomerReservation(eCus);
                    } catch (IllegalArgumentException | NullPointerException e) {
                        System.out.println("Email is invalid or non-exist. Please try again. Email should be formatted as something@domain.com");
                    }
                }
                case 3 -> {
                    try {
                        System.out.println("Enter email formatted as something@domain.com");
                        String emailCus = INPUT.getStringInput();
                        System.out.println("What is your first name?");
                        String firstName = INPUT.getStringInput();
                        System.out.println("What is your last name?");
                        String lastName = INPUT.getStringInput();
                        HOTEL_RESOURCE.createCustomer(emailCus, firstName, lastName);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Email is invalid. Please try again. Email should be formatted as something@domain.com");
                    }
                }
                case 4 -> AdminMenu.adminMenu();
                case 5 -> System.exit(0);
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("Welcome to the Hotel Reservation Application");
        System.out.println("--------------------------------------------------");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.println("--------------------------------------------------");
        System.out.println("Please select a number for the menu option");
    }
}
