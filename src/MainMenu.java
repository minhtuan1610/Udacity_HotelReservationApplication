import api.DataInput;
import api.HotelResource;

import java.text.ParseException;
import java.util.Date;


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
					try {
						System.out.println("Enter CheckIn Date dd/MM/yyyy. Example: 02/01/2024");
						checkIn = INPUT.getDateInput();
						System.out.println("Enter CheckOut Date dd/MM/yyyy. Example: 02/01/2024");
						checkOut = INPUT.getDateInput();
					} catch (ParseException e) {
						System.out.println("Wrong format date");
						e.printStackTrace();
					}
					HOTEL_RESOURCE.findARoom(checkIn, checkOut);
					// Reserve a room
					System.out.println("Would you like to book a room? (Please type y/n)");
					String text = INPUT.getStringInput();
					while (!text.equalsIgnoreCase("y") && !text.equalsIgnoreCase("n")) {
						System.out.println("Would you like to book a room? (Please type y/n)");
						text = INPUT.getStringInput();
					}
					if (text.equalsIgnoreCase("y")) {
						System.out.println("Enter your email:");
						String email = INPUT.getStringInput();
						System.out.println("Choose room number:");
						HOTEL_RESOURCE.bookARoom(email, HOTEL_RESOURCE.getRoom(INPUT.getStringInput()), checkIn, checkOut);
					}
				}
				case 2 -> {
					// See my reservations
					System.out.println("Enter your email:");
					String eCus = INPUT.getStringInput();
					HOTEL_RESOURCE.getCustomerReservations(eCus);
				}
				case 3 -> {
					System.out.println("Enter email formatted as something@domain.com");
					String emailCus = INPUT.getStringInput();
					System.out.println("What is your first name?");
					String firstName = INPUT.getStringInput();
					System.out.println("What is your last name?");
					String lastName = INPUT.getStringInput();
					HOTEL_RESOURCE.createCustomer(emailCus, firstName, lastName);
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
